/*
 * Confirmação de exclusão de um contato
 * @author Marcos Melo
 */

function confirmar(idcon) {
    let resposta = confirm("Tem certeza que deseja excluir este contato?");
    if ( resposta === true) {
        //alert(idcon)
        window.location.href = 'delete?idcon=' + idcon
    }
}
