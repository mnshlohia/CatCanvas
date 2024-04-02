package com.example.catcanvas.presentation.cat_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catcanvas.R
import com.example.catcanvas.ui.theme.Purple80
import com.example.catcanvas.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListingsScreen(
    viewModel: CatListingViewmodel = hiltViewModel()
) {

    val state = viewModel.state
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Purple80
            ),
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Purple80),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Cursive
                        )
                    )
                }
            )
        }
    ) { values ->
        LazyColumn(
            modifier = Modifier
                .background(
                    color = Color.Transparent
                )
                .fillMaxSize()
                .padding(values)

        ) {
            items(state.cats.size) { i ->
                val cat = state.cats[i]
                CatListingItem(cat,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }

}