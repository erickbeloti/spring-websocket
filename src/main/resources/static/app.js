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
        let tr =  `
            <tr>
                <td>${new Intl.NumberFormat('pt-BR').format(myObj[x].numero)}</td>
                <td>${formatPhone(myObj[x].origem)}</td>
                <td>${myObj[x].destino}</td>
                <td>${myObj[x].dataHora}</td>
            </tr>
        `;
        $("#tb-body-protocolos").append(tr);
    }
}

function formatPhone(number) {
    if (number.length == 11) {
        number = `(${number.substr(0,2)}) ${number.substr(2,5)} - ${number.substr(7)}`
    } else if (number.length == 10) {
        number = `(${number.substr(0,2)}) ${number.substr(2,4)} - ${number.substr(6)}`
    }

    return number;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#monitorar").click(function() { connect(); });
    $("#cancelar").click(function() { disconnect(); });
    $("#div-table").hide();
});