package com.cherlan.quote.ui.quote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherlan.quote.data.model.Quote

@Composable
fun QuoteScreen(quoteViewModel: QuoteViewModel) {
    val quotes by quoteViewModel.quotes.observeAsState(listOf())

    LazyColumn {
        items(quotes) { quote ->
            QuoteItem(quote)
        }
    }

}


@Composable
fun QuoteItem(quote: Quote) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { /* Handle category click event here */ },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = quote.quote ?: "Unknown",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
            Text(
                text = quote.author ?: "Unknown",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
        }
    }
    Spacer(modifier = Modifier.width(14.dp))
}