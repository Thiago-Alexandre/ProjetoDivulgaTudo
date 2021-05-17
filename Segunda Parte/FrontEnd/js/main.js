$(document).ready(function(){
    
    $(function(){

        carregar("http://localhost:8080/DivulgaTudo/anuncios/pesquisar");
    });

    $("#pesquisarAnuncios").click(function(){

        cliente = $("#pesquisaPorCliente").val();
        dataInicial = $("#dataInicialPeriodo").val();
        dataFinal = $("#dataFinalPeriodo").val();

        $("#pesquisaPorCliente").val("");
        $("#dataInicialPeriodo").val("");
        $("#dataFinalPeriodo").val("");

        if (cliente !== ""){
            carregar("http://localhost:8080/DivulgaTudo/anuncios/pesquisar?cliente=" + cliente);
        } else if (dataInicial !== "" && dataFinal !== ""){
            carregar("http://localhost:8080/DivulgaTudo/anuncios/pesquisar?inicioPeriodo=" + dataInicial + "&finalPeriodo=" + dataFinal);     
        } else{
            carregar("http://localhost:8080/DivulgaTudo/anuncios/pesquisar");
        }
    });

    function carregar(url){

        $.get(url,function(data,status){

            if (data.length === 0){
                $("#listaAnuncios").html("<tr><td colspan=9>Nenhum Anúncio encontrado!</td></tr>");
            } else{
                var table = "";
                for(x in data){
                    table += "<tr>" +
                                "<td>" + data[x].nome + "</td>" +
                                "<td>" + data[x].cliente + "</td>" +
                                "<td>" + data[x].dataInicio + "</td>" +
                                "<td>" + data[x].dataTermino + "</td>" +
                                "<td>" + data[x].investimentoPorDia + "</td>" +
                                "<td>" + data[x].valorTotalInvestido + "</td>" +
                                "<td>" + data[x].quantidadeMaximaVisualizacoes + "</td>" +
                                "<td>" + data[x].quantidadeMaximaCliques + "</td>" +
                                "<td>" + data[x].quantidadeMaximaCompartilhamentos + "</td>" +
                            "</tr>";
                }
                $("#listaAnuncios").html(table);
            }
        });
    };

    $(document).on("click", "#salvarAnuncio", function() {

        nomeAnuncio = $("#nomeAnuncio").val();
        clienteAnuncio = $("#clienteAnuncio").val();
        dataInicialAnuncio = $("#dataInicialAnuncio").val();
        dataFinalAnuncio = $("#dataFinalAnuncio").val();
        investimentoDiaAnuncio = $("#investimentoDiaAnuncio").val();

        metodo = "POST";
        endpoint = "http://localhost:8080/DivulgaTudo/anuncios/novo";
        var dados = JSON.stringify({nome: nomeAnuncio, 
                                    cliente: clienteAnuncio, 
                                    dataInicio: dataInicialAnuncio, 
                                    dataTermino: dataFinalAnuncio, 
                                    investimentoPorDia: investimentoDiaAnuncio}); 
        $.ajax({ 
            url: endpoint, 
            type: metodo, 
            dataType: "json",
            contentType: "application/json",
            data: dados, 
            success: sucessoSalvar,
            error: erroSalvar 
        });

        function sucessoSalvar (retorno) {

            fecharModal();
            carregar("http://localhost:8080/DivulgaTudo/anuncios/pesquisar");
        }

        function erroSalvar (retorno) {

            fecharModal();
            alert("Erro ao salvar novo anúncio!");
        }
    });

    $(document).on("click", "#cancelar", function() {

        fecharModal();
    });

    $(document).on("click", "#fechar", function() {

        fecharModal();
    });

    function fecharModal(){

        $("#nomeAnuncio").val("");
        $("#clienteAnuncio").val("");
        $("#dataInicialAnuncio").val("");
        $("#dataFinalAnuncio").val("");
        $("#investimentoDiaAnuncio").val("");
        $('#anuncioModal').modal('hide');
    };
});