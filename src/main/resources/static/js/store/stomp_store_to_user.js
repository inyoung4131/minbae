var stompClient = null;
var userIdx = null; // 여기에 두면 될까..

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        const storeIdx = window.sessionStorage.getItem("storeIdx");
        stompClient.subscribe(`/topic/${storeIdx}`, function (greeting) {
            console.log(JSON.parse(greeting.body).name);
            userIdx = JSON.parse(greeting.body).name; // 추가
            showGreeting(JSON.parse(greeting.body).name);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/"+userIdx, {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); }); // 로그아웃 되거나, 다른 가게를 선택 시
    $( "#send" ).click(function() { sendName(); });
});

