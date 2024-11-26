package vn.edu.hust.studentman

import android.media.Image
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val context: MainActivity, val students: List<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val removeButton: ImageView = itemView.findViewById(R.id.image_more)
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
      parent, false)
    return StudentViewHolder(itemView)
  }
  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
    holder.itemView.findViewById<ImageView>(R.id.image_more).setOnClickListener {
      showPopupMenu(it, student)
    }
  }
  private fun showPopupMenu(view: View, studentModel: StudentModel) {
    val popupMenu = PopupMenu(view.context, view)
    popupMenu.menuInflater.inflate(R.menu.menu_item_selected, popupMenu.menu)

    // Xử lý sự kiện khi chọn các mục trong menu
    popupMenu.setOnMenuItemClickListener { menuItem ->
      when (menuItem.itemId) {
        R.id.EditItem -> {
          context.showEditStudentDialog(studentModel)
          true
        }
        R.id.RemoveItem -> {
          context.showDeleteConfirmation(studentModel)
          true
        }
        else -> false
      }
    }

    popupMenu.show()
  }
}