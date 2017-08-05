package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.trabalho.bd2.dao.AluguelDAO;
import br.com.trabalho.bd2.dao.ClienteDAO;
import br.com.trabalho.bd2.dao.VeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Aluguel;
import br.com.trabalho.bd2.model.AluguelRel;
import br.com.trabalho.bd2.model.Cliente;
import br.com.trabalho.bd2.model.Veiculo;

@ManagedBean
@ViewScoped
public class AluguelController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluguel aluguel;
	
	private Aluguel aluguelSelecionado;

	private List<Aluguel> alugueis;
	
	private List<Veiculo> veiculos = new ArrayList<>();;
	
	private List<Cliente> clientes = new ArrayList<>();
	
	private AluguelDAO aluguelDAO = new AluguelDAO();
	
	private VeiculoDAO veiculoDAO = new VeiculoDAO();
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private BarChartModel barModel;
    
    private int mes;
    private int ano;
	
	@PostConstruct
	public void inicializar() {
		limpar();
		pieModel1 = new PieChartModel();
		pieModel2 = new PieChartModel();
		barModel = new BarChartModel();
		try {
			veiculos = veiculoDAO.buscarTodos();
		} catch (Exception e1) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " +e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			clientes = clienteDAO.buscarTodos();
		} catch (Exception e1) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " +e1.getMessage());
			e1.printStackTrace();
		}
		
		
		try {
			alugueis = aluguelDAO.buscarTodos();
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " +e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void salvar() {
		try {
			
			String s = null;
			s = aluguelDAO.buscarPorId(aluguel.getVeiculo().getPlaca(), aluguel.getCliente().getCodC()).getNroCnh();

			if (s == null) {
				aluguelDAO.salvar(aluguel);
				FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!");
			} else {
				aluguelDAO.editar(aluguel);
				FacesUtil.addSuccessMessage("Aluguel Atualizado com sucesso!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() throws Exception {
		try {
			aluguelDAO.excluir(aluguelSelecionado);
			this.alugueis.remove(aluguelSelecionado);
			FacesUtil.addSuccessMessage("Aluguel do cliente " + aluguelSelecionado.getCliente().getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		alugueis = aluguelDAO.buscarTodos();
	}

	public void limpar() {
		this.aluguel = new Aluguel();
	}
	
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Aluguel Editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        Aluguel al = (Aluguel) event.getObject();
        
        try {
			aluguelDAO.editar(al);
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao atualizar");
			e.printStackTrace();
		}
    }
	
	
	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
   
    public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public void createPieModel1() { 
		pieModel1 = new PieChartModel();
        try {
        	
        	List<AluguelRel> al = aluguelDAO.relatorioMes(mes, ano);
        	
        	if(al.isEmpty()){
        		 pieModel1.set("Sem Dados", 1);
        	}
			for(AluguelRel a: aluguelDAO.relatorioMes(mes, ano))
				pieModel1.set(a.getMes(), a.getQtdMes());
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
         
        pieModel1.setTitle("Relatório Mensal");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
       
    }
     
    public void createPieModel2() {
    	pieModel2 = new PieChartModel();
        try {
        	
        	List<AluguelRel> al = aluguelDAO.relatorioVeiculo(mes, ano);
        	
        	if(al.isEmpty()){
        		 pieModel2.set("Sem Dados", 1);
        	}
        	
			for(AluguelRel a: aluguelDAO.relatorioVeiculo(mes, ano))
				pieModel2.set(a.getVeiculo(), a.getQtdMesVeiculo());
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
         
        pieModel2.setTitle("Relatório Anual");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    
    
    public void createPieModel3() { 
		barModel = new BarChartModel();
        try {
        	
        	List<AluguelRel> al = aluguelDAO.relatorioMediaAnual(ano);
        	
        	if(al.isEmpty()){
        		ChartSeries m = new ChartSeries();
        		m.set("Sem Dados", 1);
        		barModel.addSeries(m);
        	}
        	
			for(AluguelRel a: aluguelDAO.relatorioMediaAnual(ano)){
				ChartSeries m = new ChartSeries();
				m.setLabel(String.valueOf(a.getAno()));
				m.set(String.valueOf(a.getAno()), a.getQtdAno());
				barModel.addSeries(m);
			}
			
			barModel.setTitle("Relatório Anual");
	        barModel.setLegendPosition("ne");
			
			 Axis xAxis = barModel.getAxis(AxisType.X);
		        xAxis.setLabel("Ano");
			
			Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantidade");
	        yAxis.setMin(0);
	        yAxis.setMax(100);
			
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
       
    }
	
	
	
}
