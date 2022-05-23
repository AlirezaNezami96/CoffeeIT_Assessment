package alireza.nezami.coffeeit_assessment.presentation.adapter

import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.SelectedCoffeeExtra
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation


@BindingAdapter("imageIsVisible")
fun imageIsVisible(view: View, isVisible: Boolean) {
    if (isVisible) view.visibility = View.VISIBLE
    else view.visibility = View.INVISIBLE
}


@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    if (isVisible) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter(value = ["setImageUrl", "setCircleShape"], requireAll = false)
fun setImageUrl(imageView: ImageView, resourceId: Int?, circleShape: Boolean = true) {
    if (resourceId == null) {
        imageIsVisible(imageView, false)
    } else {
        imageView.load(resourceId) {
            if (circleShape) {
                transformations(CircleCropTransformation())
            }
        }
    }
}

fun ImageView.setImageDrawableResource(resourceId: Int?) {
    setImageUrl(this, resourceId)
}

