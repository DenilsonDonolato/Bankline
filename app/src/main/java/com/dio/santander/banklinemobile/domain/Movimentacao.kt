package com.dio.santander.banklinemobile.domain

data class Movimentacao(
    val id: Int,
    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipo: TipoMovimentacao,
    val idCorrentista: Int
)
