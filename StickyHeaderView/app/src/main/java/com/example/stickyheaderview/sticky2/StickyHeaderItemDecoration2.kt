package com.example.stickyheaderview.sticky2

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderItemDecoration2 (
    private val sectionCallback: SectionCallback
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)

        val currentHeader: View = sectionCallback.getHeaderLayoutView(parent, topChildPosition) ?: return

        fixLayoutSize(parent, currentHeader)

        val contactPoint = currentHeader.bottom
        val childInContact: View = (getChildInContact(parent, contactPoint) ?: return)

        val childAdapterPosition = parent.getChildAdapterPosition(childInContact)
        if (childAdapterPosition == -1) return

        if (sectionCallback.isHeader(childAdapterPosition)) {
            moveHeader(c, currentHeader, childInContact)
            return
        }
        drawHeader(c, currentHeader)
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (child.bottom > contactPoint) {
                if (child.top <= contactPoint) {
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View) {
        c.save()
        c.translate(0f, nextHeader.top - currentHeader.height.toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun drawHeader(c: Canvas, header: View) {
        c.save()
        c.translate(0f, 0f)
        header.draw(c)
        c.restore()
    }

    private fun fixLayoutSize(parent: ViewGroup, view: View) {

        val viewWidthSpec = View.MeasureSpec.makeMeasureSpec(
            parent.width,
            View.MeasureSpec.EXACTLY
        )

        val viewHeightSpec = View.MeasureSpec.makeMeasureSpec(
            parent.height,
            View.MeasureSpec.AT_MOST
        )

        val childWidth: Int = ViewGroup.getChildMeasureSpec(
            viewWidthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )

        val childHeight: Int = ViewGroup.getChildMeasureSpec(
            viewHeightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )

        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    interface SectionCallback {
        fun isHeader(position: Int): Boolean
        fun getHeaderLayoutView(list: RecyclerView, position: Int): View?
    }

}