package GameCollection.Abstract.Records;

import GameCollection.Abstract.Enums.CheckDirection;

public record WinConditions (Integer row, Integer column, CheckDirection direction) {
}
