package com.uv.apnidukaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uv.apnidukaan.R
import com.uv.apnidukaan.model.Transaction

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvType: TextView = itemView.findViewById(R.id.tvType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.tvItemName.text = transaction.itemName
        holder.tvQuantity.text = "Qty: ${transaction.quantity}"
        holder.tvPrice.text = "₹${transaction.price}"
        holder.tvDate.text = transaction.date
        holder.tvType.text = if (transaction.type == "SELL") "🛒 Sold" else "📦 Bought"

        if (transaction.type == "SELL") {
            holder.tvType.setTextColor(holder.itemView.context.getColor(android.R.color.holo_green_dark))
        } else {
            holder.tvType.setTextColor(holder.itemView.context.getColor(android.R.color.holo_red_dark))
        }
    }

    override fun getItemCount(): Int = transactions.size
}
