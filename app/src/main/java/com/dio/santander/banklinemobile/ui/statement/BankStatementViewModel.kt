package com.dio.santander.banklinemobile.ui.statement

import androidx.lifecycle.ViewModel
import com.dio.santander.banklinemobile.data.BanklineRepository

class BankStatementViewModel : ViewModel() {

    fun findBankStatement(accountHolderId: Int) = BanklineRepository.findBankStatement(accountHolderId)
}