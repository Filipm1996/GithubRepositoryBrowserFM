package com.example.githubrepositorybrowserfm.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.githubrepositorybrowserfm.R
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.ui.theme.Typography

@Composable
fun RepositoryItem(repositoryInfo: RepositoryInfo, onItemClick: ((RepositoryInfo) -> Unit)) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.margin_0_5x))
            .clickable {
                onItemClick(repositoryInfo)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.github_mark),
                contentDescription = "github logo",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_0_5x))
                    .width(30.dp)
                    .height(30.dp)
            )
            Text(
                repositoryInfo.name!!,
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_default))
            )
        }
    }
}