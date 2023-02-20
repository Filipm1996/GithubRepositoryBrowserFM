package com.example.githubrepositorybrowserfm.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.githubrepositorybrowserfm.R
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.ui.theme.Typography

@Composable
fun DetailScreen(
    repositoryInfo: RepositoryInfo,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.github_mark),
            contentDescription = "github logo",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.margin_0_5x))
                .width(70.dp)
                .height(70.dp)
        )
        Text(
            text = repositoryInfo.name!!,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_0_5x))
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            shape = RoundedCornerShape(10),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_0_5x))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.margin_default)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.details_container_title),
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_0_5x))
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.description_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                        style = Typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.margin_0_5x),
                            bottom = dimensionResource(id = R.dimen.margin_default)
                        ),
                        text = repositoryInfo.description
                            ?: stringResource(id = R.string.empty_description),
                        style = Typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = stringResource(id = R.string.commits_quanttiy_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                        style = Typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.margin_0_5x),
                            bottom = dimensionResource(id = R.dimen.margin_default)
                        ),
                        text = repositoryInfo.commitsQty.toString(),
                        style = Typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = stringResource(id = R.string.issues_quanttiy_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                        style = Typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.margin_0_5x),
                            bottom = dimensionResource(id = R.dimen.margin_default)
                        ),
                        text = repositoryInfo.issuesQty.toString(),
                        style = Typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }

        }
    }
}