package com.example.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dialog.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            dialog.setOnClickListener {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setMessage("내용")
                    .setNegativeButton("취소") { dialog, which ->
                        //
                    }
                    .setPositiveButton("확인") { dialog, which ->
                        //
                    }
                    .show()
            }
            neutral.setOnClickListener {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setMessage("내용")
                    .setNeutralButton("취소") { dialog, which ->
                        //
                    }
                    .setNegativeButton("거절") { dialog, which ->
                        //
                    }
                    .setPositiveButton("수락") { dialog, which ->
                        //
                    }
                    .show()
            }
            list.setOnClickListener {
                val items = arrayOf("1", "2", "3")

                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle("선택")
                    //.setMessage("DO NOT SET MESSAGE WHEN YOU USE LIST")
                    .setItems(items) { dialog, which ->
                        showSnackBar(items[which])
                    }
                    .show()
            }
            radio.setOnClickListener {
                val items = arrayOf("1", "2", "3")
                var itemIndex = 0

                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle("선택")
                    .setNeutralButton("취소") { dialog, which ->
                        //
                    }
                    .setPositiveButton("확인") { dialog, which ->
                        showSnackBar(items[itemIndex])
                    }
                    .setSingleChoiceItems(items, itemIndex) { dialog, which ->
                        itemIndex = which
                    }
                    .show()
            }
            checkbox.setOnClickListener {
                val items = arrayOf("1", "2", "3")
                val itemsChecked = booleanArrayOf(true, false, false, false)

                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle("선택")
                    .setNeutralButton("취소") { dialog, which ->
                        //
                    }
                    .setPositiveButton("확인") { dialog, which ->
                        val count = itemsChecked.count { it }
                        showSnackBar("$count")
                    }
                    .setMultiChoiceItems(items, itemsChecked) { dialog, which, checked ->
                        itemsChecked[which] = checked
                    }
                    .show()
            }
            custom.setOnClickListener {
                val dialog = CustomDialog(this@MainActivity)
                dialog.onItemClick = { msg ->
                    showSnackBar(msg)
                }
                dialog.show()
            }
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}