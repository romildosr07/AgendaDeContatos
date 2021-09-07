/**
 * confirmaçao de exclusao
 */
function confirmar(idcon){
	let resposta = confirm("Deseja excluir o contato?")
	if(resposta){
		window.location.href = "delete?idcon=" + idcon
	}
}