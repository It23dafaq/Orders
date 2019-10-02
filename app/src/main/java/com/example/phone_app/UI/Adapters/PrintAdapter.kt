package com.example.phone_app.UI.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.pdf.PrintedPdfDocument
import com.example.phone_app.Data.Products
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import android.print.PrintAttributes as PrintAttributes1

class PrintAdapter(
    private var context: Context,
    cart: MutableList<Products>,
    id: String,
    getPrice: BigDecimal
): PrintDocumentAdapter() {
    private var drinks = cart
    private var id =id
    private var pageHeight: Int = 0
    private var pageWidth: Int = 0
    private var myPdfDocument: PdfDocument? = null
    private var totalpages = 1
    private var total=getPrice
    override fun onLayout(
        oldAttributes: PrintAttributes1,
        newAttributes: PrintAttributes1,
        cancellationSignal: CancellationSignal,
        callback: LayoutResultCallback?,
        extras: Bundle?
    ) {
        myPdfDocument = PrintedPdfDocument(context!!, newAttributes)
        val height = newAttributes.mediaSize?.heightMils
        val width = newAttributes.mediaSize?.heightMils

        height?.let {
            pageHeight = it / 1000 * 72
        }

        width?.let {
            pageWidth = it / 1000 * 72
        }

        if (cancellationSignal!!.isCanceled) {
            callback?.onLayoutCancelled()
            return
        }

        if (totalpages > 0) {
            val builder =
                PrintDocumentInfo.Builder("print_output.pdf").setContentType(
                    PrintDocumentInfo.CONTENT_TYPE_DOCUMENT
                )
                    .setPageCount(totalpages)

            val info = builder.build()
            callback?.onLayoutFinished(info, true)
        } else {
            callback?.onLayoutFailed("Page count is zero.")
        }
    }


    override fun onWrite(
        pageRanges: Array<PageRange>,
        destination: ParcelFileDescriptor,
        cancellationSignal: CancellationSignal,
        callback: WriteResultCallback?
    ) {
        for (i in 0 until totalpages) {
            if (pageInRange(pageRanges, i)) {
                val newPage = PdfDocument.PageInfo.Builder(
                    pageWidth,
                    pageHeight, i
                ).create()

                val page = myPdfDocument?.startPage(newPage)

                if (cancellationSignal.isCanceled) {
                    callback?.onWriteCancelled()
                    myPdfDocument?.close()
                    myPdfDocument = null
                    return
                }
                page?.let {
                    drawPage(it, i,drinks,id)
                }
                myPdfDocument?.finishPage(page)
            }
        }

        try {
            myPdfDocument?.writeTo(
                FileOutputStream(
                    destination.fileDescriptor
                )
            )
        } catch (e: IOException) {
            callback?.onWriteFailed(e.toString())
            return
        } finally {
            myPdfDocument?.close()
            myPdfDocument = null
        }

        callback?.onWriteFinished(pageRanges)
    }


    private fun pageInRange(pageRanges: Array<PageRange>, page: Int): Boolean {
        for (i in pageRanges.indices) {
            if (page >= pageRanges[i].start && page <= pageRanges[i].end)
                return true
        }
        return false
    }

    private fun drawPage(
        page: PdfDocument.Page,
        pagenumber: Int, cart: MutableList<Products>, id: String
    ) {
        var pagenum = pagenumber
        val canvas = page.canvas

        pagenum++ // Make sure page numbers start at 1

        val titleBaseLine = 72
        val leftMargin = 54

        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 30f
        canvas.drawText(
            "Order Table ID"+" "+id,
            leftMargin.toFloat(),
            titleBaseLine.toFloat(),
            paint
        )
        paint.textSize = 16f
        var distancebetweenGroup:Int=0
        var counter=0
        var distancebetween=0
        cart.forEachIndexed{ index, products ->

            if(index!=0){
                distancebetween+=20
            }
                distancebetweenGroup += 30
                canvas.drawText(
                    "Drinks  :" + products.name, leftMargin.toFloat(),
                    (titleBaseLine + ( distancebetweenGroup+distancebetween)).toFloat(),
                    paint
                )
                canvas.drawText(
                    "Quantity  :" + products.quantity, leftMargin.toFloat(),
                    (titleBaseLine + (15 + distancebetweenGroup+distancebetween)).toFloat(),
                    paint
                )

                canvas.drawText(
                    "Price  :" + products.price, leftMargin.toFloat(),
                    (titleBaseLine + (30 + distancebetweenGroup+distancebetween)).toFloat(),
                    paint
                )




        }
/*
        paint.textSize = 40f
        canvas.drawText("Drinks  :" +Drinks, leftMargin.toFloat()-10f,
            (titleBaseLine + 35).toFloat(),
            paint)
        canvas.drawText("Quantity  :" +Quantity, leftMargin.toFloat()-10f,
            (titleBaseLine + 80).toFloat(),
            paint)

        canvas.drawText("Price  :" +Price, leftMargin.toFloat()-10f,
            (titleBaseLine + 125).toFloat(),
            paint)
            */
    /*    canvas.drawText(
                   "Drinks  :" +Drinks+ "Quantity" +Quantity +
                           "Price   :"+ Price
            ,

            leftMargin.toFloat(),
            (titleBaseLine + 35).toFloat(),
            paint
        )
        */

      if (pagenum % 2 == 0)
            paint.color = Color.RED
        else
            paint.color = Color.GREEN

        val pageInfo = page.info


       /* canvas.drawCircle(
            (pageInfo.pageWidth / 2).toFloat(),
            (pageInfo.pageHeight / 2).toFloat(),
            150f,
            paint
        )
        */
    }

}