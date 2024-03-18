package com.example.viewpagertransformation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

@BindingAdapter("android:src")
fun bindImageView(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .centerCrop()
        .into(view)
}

fun listOfImages(): List<Image> {
    val images = mutableListOf<Image>()

    images.add(
        Image(
            "https://images.unsplash.com/photo-1589491106922-a8e488665b2c?ixlib=rb-1.2.1&auto=format&fit=crop&w=668&q=80",
            Credit("Bechir Kaddech", "@bechir", "https://unsplash.com/ko/@bechir"),
            "Marina, Sidi bousaid, Tunisia"
        )
    )

    images.add(
        Image(
            "https://images.unsplash.com/photo-1556011572-d786c300819f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=668&q=80",
            Credit("Adrian Dascal", "@dascal", "https://unsplash.com/ko/@dascal"),
            "Downtown, Tunis, Tunisia"
        )
    )

    images.add(
        Image(
            "https://images.unsplash.com/photo-1540552999122-a0ac7a9a0008?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=668&q=80",
            Credit("Janik Lierfeld", "@janiklie", "https://unsplash.com/ko/@janiklie"),
            "Mahdia, Tunisia"
        )
    )

    images.add(
        Image(
            "https://images.unsplash.com/photo-1523971436722-f144a6a5dc60?ixlib=rb-1.2.1&auto=format&fit=crop&w=1295&q=80",
            Credit("Naomi Koelemans", "@naomikoelemans", "https://unsplash.com/ko/@naomikoelemans"),
            "Tozeur, Tunisia"
        )
    )

    images.add(
        Image(
            "https://images.unsplash.com/photo-1556901599-6cd86f3da8b1?ixlib=rb-1.2.1&auto=format&fit=crop&w=2533&q=80",
            Credit("Haythem Gataa", "@haythemgataa", "https://unsplash.com/ko/@haythemgataa"),
            "Mosque Okba, kairouan, Tunisia"
        )
    )

    images.add(
        Image(
            "https://images.unsplash.com/photo-1565689478170-6624de957899?ixlib=rb-1.2.1&auto=format&fit=crop&w=668&q=80",
            Credit("Adrian Dascal", "@dascal", "https://unsplash.com/ko/@dascal"),
            "Avenue, Hammamet, Tunisia"
        )
    )

    return images
}