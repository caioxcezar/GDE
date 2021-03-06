var produtos = $("#hiddenProdutos");
var searchParams = new URLSearchParams(window.location.search);

(function () {
    if (window.location.pathname.indexOf("manterPedido") > 0) {
        updateLista();
        if ($("#inputEstado").val() === "Pago")
            bloquearInput();
    } 
    if (searchParams.get("operacao") === "excluir")
        bloquearInput();
    resizeConteudo();
})();

function updateLista() {
    let lista = "<ul class='list-group'>";
    if (produtos.val() !== "") {
        produtos.val().split(";").forEach(e => {
            if (e !== "") {
                let produto = e.split(",");
                let nome = $(`#inputProduto option[value='${produto[0]}']`).text();
                lista += `<li class="list-group-item">Produto: ${nome} Quantidade: ${produto[1]}`;
                lista += `<button class='btn btn-danger ml-2 text-white btn-list' onclick='removerProduto(${produto[0]})'>Remover</button></li>`;
            }
        });
    }
    $("#listaProd").html(lista + "</ul>");
}
function removerProduto(id) {
    let newList = "";
    produtos.val().split(";").forEach(e => {
        if (e !== "") {
            let produto = e.split(",");
            if (parseInt(produto[0]) !== id) {
                newList += `${produto[0]},${produto[1]};`;
            }
        }
    });
    produtos.val(newList);
    updateLista();
}
function adicionarProduto() {
    let quantidade = parseInt($("#inputQuantidade").val());
    let produto = $("#inputProduto").val();
    let update = false;
    if (quantidade > 0 && produto !== "") {
        let newList = "";
        produtos.val().split(";").forEach(e => {
            if (e !== "") {
                let produtoAtual = e.split(",");
                if (produtoAtual[0] !== produto) {
                    newList += `${produtoAtual[0]},${produtoAtual[1]};`;
                } else {
                    newList += `${produtoAtual[0]},${parseInt(produtoAtual[1]) + quantidade};`;
                    update = true;
                }
            }
        });
        if (!update) {
            newList += `${produto},${quantidade};`;
        }
        produtos.val(newList);
        updateLista();
    }
}

$('form').on('submit', function () {
    $('select').prop('disabled', false);
});

function bloquearInput() {
    $("input").prop('readonly', true);
    $("select").prop('disabled', true);
    $("textarea").prop('readonly', true);
    $(".btn-list").prop('disabled', true);
}

function resizeConteudo() {
    let conteudo = $(".conteudo");
    let headerBar = $("#headerBar").outerHeight(true);
    let footerBar = $("#footerBar").outerHeight(true);
    $(".conteudo").css("min-height", `calc(100vh - ${headerBar + footerBar}px)`);
}