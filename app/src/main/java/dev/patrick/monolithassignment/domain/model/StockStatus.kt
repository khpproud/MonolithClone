package dev.patrick.monolithassignment.domain.model

enum class StockStatus(val koreanStr: String) {
    SOLDOUT("매진"),
    CROWDED("혼잡"),
    NORMAL("보통"),
    RELAXED("여유");

    companion object {
        fun parseStockStatus(stockStatus: String): StockStatus {
            return when (stockStatus) {
                "재고부족" -> SOLDOUT
                "혼잡" -> CROWDED
                "보통" -> NORMAL
                "여유" -> RELAXED
                else -> throw Exception("Not available Stock status type")
            }
        }
    }
}