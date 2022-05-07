package com.dio.santander.banklinemobile.ui.statement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dio.santander.banklinemobile.databinding.ActivityBankStatamentBinding
import com.dio.santander.banklinemobile.domain.Correntista
import com.dio.santander.banklinemobile.domain.Movimentacao
import com.dio.santander.banklinemobile.domain.TipoMovimentacao

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "com.dio.santander.banklinemobile.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatamentBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)
        findBankStatement()
    }

    private fun findBankStatement() {
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1,"03/05/2022 09:24:55","Exemplo", 1000.00,TipoMovimentacao.RECEITA,1))
        dataSet.add(Movimentacao(2,"03/05/2022 09:24:55","Exemplo", 50.00,TipoMovimentacao.DESPESA,1))
        dataSet.add(Movimentacao(3,"03/05/2022 09:24:55","Exemplo", 10.00,TipoMovimentacao.DESPESA,1))
        dataSet.add(Movimentacao(4,"03/05/2022 09:24:55","Exemplo", 500.00,TipoMovimentacao.RECEITA,1))
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}