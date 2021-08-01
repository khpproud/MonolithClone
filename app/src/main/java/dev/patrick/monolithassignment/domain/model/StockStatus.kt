package dev.patrick.monolithassignment.domain.model

enum class StockStatus(val koreanStr: String) {
    SOLDOUT("매진"),
    CROWDED("혼잡"),
    NORMAL("보통"),
    RELAXED("여유")
}