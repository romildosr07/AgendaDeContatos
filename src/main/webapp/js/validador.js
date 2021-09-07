/**
 * validaçao
 */
function validar(){
	let nome = forme.nome.value
	let telefone = forme.telefone.value
	let email = forme.email.value
	if(nome === ""){
		alert("Preencha o campo do nome!")
		forme.nome.focus()
		return false
	}
	else if(telefone === ""){
		alert("preencha o campo telefone")
		forme.telefone.focus()
		return false
	}
	else{
		document.forms["forme"].submit()
	}
	
}