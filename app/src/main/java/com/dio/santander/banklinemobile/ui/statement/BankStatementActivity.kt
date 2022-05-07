package com.dio.santander.banklinemobile.ui.statement

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dio.santander.banklinemobile.data.State
import com.dio.santander.banklinemobile.databinding.ActivityBankStatamentBinding
import com.dio.santander.banklinemobile.domain.Correntista
import com.dio.santander.banklinemobile.domain.Movimentacao
import com.dio.santander.banklinemobile.domain.TipoMovimentacao
import com.google.android.material.snackbar.Snackbar

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

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)
        findBankStatement()

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
    }

    private fun findBankStatement() {
        Log.d("REFRESH","start")
        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when(state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                    Log.d("REFRESH","success")
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                    binding.srlBankStatement.isRefreshing = false
                    Log.d("REFRESH","error")
                }
                State.Wait -> {
                    binding.srlBankStatement.isRefreshing = true
                    Log.d("REFRESH","error")
                }
            }

        }
    }
}