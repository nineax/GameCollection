var turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
var turn = "";
var gameOn = false;

function playerTurn(turn, id) {
    if (gameOn) {
        var spotTaken = $("#" + id).text();
        if (spotTaken === "#") {
                makeAMove(playerType, id.split("_")[0], id.split("_")[1]);
        }
    }
}

function makeAMove(type, xCoordinate, yCoordinate) {
    $.ajax({
        url: url + "/games/tic-tac-toe/turn",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameID": gameID,
            "fieldStatus": type,
            "target": {
            "row": xCoordinate,
            "column": yCoordinate
            }
        }),
        success: function (data) {
            gameOn = false;
            displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {
    let board = data.board;
    for (let i = 0; i < board.playField.length; i++) {
        for (let j = 0; j < board.playField[i].length; j++) {
            if (String(board.playField[i][j]) === 'X') {
                turns[i][j] = 'X'
            } else if (String(board.playField[i][j]) === 'O') {
                turns[i][j] = 'O';
            }
            else
                turns[i][j] = '#';
            let id = i + "_" + j;
            $("#" + id).text(turns[i][j]);
        }
    }
    if (data.winner != null) {
        alert("Winner is " + data.winner.name);
    }
    gameOn = true;
}

$(".tic").click(function () {
    var slot = $(this).attr('id');
    playerTurn(turn, slot);
});

function displayEmptyBoard(){
    turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
    $(".tic").text("#");
}

$("#reset").click(function () {
    reset(gameID);
});
