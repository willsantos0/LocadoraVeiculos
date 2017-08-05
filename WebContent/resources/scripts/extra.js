function dateMask(inputData, e) {
	if (document.all) // Internet Explorer
		var tecla = event.keyCode;
	else
		// Outros Browsers
		var tecla = e.which;

	if (tecla >= 47 && tecla < 58) { // numeros de 0 a 9 e "/"
		var data = inputData.value;
		if (data.length == 2 || data.length == 5) {
			data += '/';
			inputData.value = data;
		}
	} else if (tecla == 8 || tecla == 0) // Backspace, Delete e setas
											// direcionais(para mover o cursor,
											// apenas para FF)
		return true;
	else
		return false;
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////

function horaMask(inputHora, e) {
	if (document.all) // Internet Explorer
		var tecla = event.keyCode;
	else
		// Outros Browsers
		var tecla = e.which;

	if (tecla >= 47 && tecla < 58) { // numeros de 0 a 9 e "/"
		var hora = inputHora.value;
		if (hora.length == 2) {
			hora += ':';
			inputHora.value = hora;
		}
	} else if (tecla == 8 || tecla == 0) // Backspace, Delete e setas
											// direcionais(para mover o cursor,
											// apenas para FF)
		return true;
	else
		return false;
}

// /////////////////////////////////////////////////////////////////////////////////////////

function mascara(o, f) {
	v_obj = o
	v_fun = f
	setTimeout("execmascara()", 1)
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value)
}

function peso(v) { // UM DIGITO DEPOIS DA VIRGULA
	v = v.replace(/\D/g, ""); // permite digitar apenas numero
	v = v.replace(/(\d{1})(\d{15})$/, "$1.$2") // coloca ponto antes dos
												// ultimos digitos
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 11 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 8 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 5 digitos
	v = v.replace(/(\d{1})(\d{1,1})$/, "$1,$2") // coloca virgula antes dos
												// ultimos 2 digitos
	return v;
}

function altura(v) { // DOIS DIGITOS DEPOIS DA VIRGULA
	v = v.replace(/\D/g, ""); // permite digitar apenas numero
	v = v.replace(/(\d{1})(\d{15})$/, "$1.$2") // coloca ponto antes dos
												// ultimos digitos
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 11 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 8 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 5 digitos
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2") // coloca virgula antes dos
												// ultimos 2 digitos
	return v;
}

// ////////////////////////////////////////////////////////
// Contador para verificar fim de sessão
var tempo = new Number();
var sessionTimeout = "<%= Session.Timeout %>";
// Tempo em segundos
tempo = 2000;
function contadorRegressivo() {
	// Se o tempo não for zerado
	if ((tempo - 1) >= 0) {
		// Pega a parte inteira dos minutos
		var min = parseInt(tempo / 60);
		// Calcula os segundos restantes
		var seg = tempo % 60;
		// Formata o número menor que dez, ex: 08, 07, ...
		if (min < 10) {
			min = "0" + min;
			min = min.substr(0, 2);
		}
		if (seg <= 9) {
			seg = "0" + seg;
		}

		// Cria a variável para formatar no estilo hora/cronômetro
		horaImprimivel = '00:' + min + ':' + seg;
		// JQuery pra setar o valor
		$("#contador").html(horaImprimivel);
		// Define que a função será executada novamente em 1000ms = 1 segundo
		setTimeout('contadorRegressivo()', 1000);
		// diminui o tempo
		tempo--;
		// Quando o contador chegar a zero faz esta ação
	} else {
		sessionTimeout = 0;
		window.open(window.location.pathname, '_self');
	}

}

function setaTempoTotal(tempototal) {
	tempo = tempototal;
}
