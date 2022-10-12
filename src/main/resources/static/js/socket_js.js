const url = 'http://localhost:8080';
let stompClient;
let gameID;
let playerType;

function connectToSocket(gameID) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay-tic-tac-toe");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/tic-tac-toe-game-progress/" + gameID, function (response) {
            let data = JSON.parse(response.body);
            displayResponse(data);
        })
    })
}

function create_game() {
    let playerName = document.getElementById("playerName").value;
    if (playerName == null || playerName === '') {
        alert("Please enter playerName");
    } else {
        $.ajax({
            url: url + "/games/tic-tac-toe/create",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "name": playerName
            }),
            success: function (data) {
                gameID = data.gameID;
                playerType = 'X';
                reset(data);
                connectToSocket(gameID);
                alert("Your created a game. Game id is: " + data.gameID);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function connectToRandom() {
    let playerName = document.getElementById("playerName").value;
    if (playerName == null || playerName === '') {
        alert("Please enter playerName");
    } else {
        $.ajax({
            url: url + "/games/tic-tac-toe/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "name": playerName
            }),
            success: function (data) {
                gameID = data.gameID;
                playerType = 'O';
                reset(data);
                connectToSocket(gameID);
                alert("Congrats you're playing with: " + data.player1.name);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame() {
    let playerName = document.getElementById("playerName").value;
    if (playerName == null || playerName === '') {
        alert("Please enter playerName");
    } else {
        let gameID = document.getElementById("game_id").value;
        if (gameID == null || gameID === '') {
            alert("Please enter game id");
        }
        $.ajax({
            url: url + "/games/tic-tac-toe/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "name": playerName
                },
                "gameID": gameID
            }),
            success: function (data) {
                gameID = data.gameID;
                playerType = 'O';
                reset(data);
                connectToSocket(gameID);
                alert("Congrats you're playing with: " + data.player1.name);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}