var stompClient = null;

function setConnected(connected) {
    $("#monitorar").prop("disabled", connected);
    $("#cancelar").prop("disabled", !connected);
    if (connected) {
        $("#div-table").show();
    }
    else {
        $("#div-table").hide();
    }
    $("#tb-body-protocolos").html("");
}

function connect() {
    let ramal = $("#fila").val();
    var socket = new SockJS("/chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Conectado: ' + frame);
        stompClient.subscribe(`/topic/messages/${ramal}`, function (messageOutput) {
            showMessageOutput(messageOutput.body);
        }, { id: ramal});
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Desconectado");
}

function showMessageOutput(json) {
    $("#tb-body-protocolos").html("");
    myObj = JSON.parse(json);
    for (x in myObj) {
        $("#tb-body-protocolos").append("<tr>");
        $("#tb-body-protocolos").append("<td>" + myObj[x].numero + "</td>");
        $("#tb-body-protocolos").append("<td>" + myObj[x].origem + "</td>");
        $("#tb-body-protocolos").append("<td>" + myObj[x].destino + "</td>");
        $("#tb-body-protocolos").append("<td>" + myObj[x].dataHora + "</td>");
        $("#tb-body-protocolos").append("</tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#monitorar").click(function() { connect(); });
    $("#cancelar").click(function() { disconnect(); });
    $("#div-table").hide();
});