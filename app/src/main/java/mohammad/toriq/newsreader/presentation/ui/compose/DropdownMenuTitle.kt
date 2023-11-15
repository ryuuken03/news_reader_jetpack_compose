package mohammad.toriq.newsreader.presentation.ui.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import mohammad.toriq.newsreader.R
import mohammad.toriq.newsreader.util.Constants

/***
 * Created By Mohammad Toriq on 15/11/2023
 */

@Preview
@Composable
@ExperimentalMaterial3Api
fun Prev(){
    DropdownMenuTitle(
        "Category",
        Constants.getCategories(),
        onClick = fun(index : Int){}
    )
}
@ExperimentalMaterial3Api
@Composable
fun DropdownMenuTitle(
    title :String?=null,
    options : List<String>,
    selectedIndex : Int = 0,
    onClick:(index : Int)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[selectedIndex]) }
    var selectedOptionIndex by remember { mutableStateOf(selectedIndex) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        if(title != null){
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = title,
                fontSize = 13.sp,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = selectedOptionText,
                onValueChange = {},
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.white),
//                    shape = RoundedCornerShape(6.dp)
                    ),
                interactionSource = interactionSource,
                readOnly = true
            ){ innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.black),
                            shape = RoundedCornerShape(6.dp)
                        )

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        innerTextField.invoke()
                        Icon(
                            icon,
                            contentDescription = "",
                            )
                    }
                }
            }
            DropdownMenu(
                modifier = Modifier
                    .background(colorResource(id = R.color.white))
                    .exposedDropdownSize(),
                expanded = expanded,
                properties = PopupProperties(
                    focusable = false,
                    dismissOnClickOutside = true,
                    dismissOnBackPress = true
                ),
                onDismissRequest = { expanded = false },
            ) {
                options.forEachIndexed  {index, select ->
                    DropdownMenuItem(
                        onClick = {
                            Log.d("OkCheck","DropdownMenuItem onClick")
                            selectedOptionIndex = index
                            selectedOptionText = select
                            expanded = false
                            onClick(index)
                        },
                        text = { Text(text = select)}
                    )
                }
            }
        }
    }

}