package minesweeper.domain

object MineSweeper {
    // TODO 리팩토링 (STEP4) - 연산 로직 개선
    fun sweep(map: List<List<Cell>>): List<List<Cell>> {
        mineCellPositions(map)
            .forEach { (x, y) ->
                map.getOrNull(x)?.getOrNull(y - 1)?.let { it.mineCountAround++ }
                map.getOrNull(x)?.getOrNull(y + 1)?.let { it.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y)?.let { it.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y)?.let { it.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y - 1)?.let { it.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y - 1)?.let { it.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y + 1)?.let { it.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y + 1)?.let { it.mineCountAround++ }
            }
        return map
    }

    private fun mineCellPositions(map: List<List<Cell>>): List<Pair<Int, Int>> {
        return map.flatMapIndexed { x: Int, cells: List<Cell> ->
            cells.mapIndexedNotNull { y: Int, cell: Cell -> if (cell is MineCell) y else null }
                .map { y -> Pair(x, y) }
        }
    }
}
