package com.example.app2.SampleProject;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<NoteModel> noteModels;
    private Context context;
    private MyDataBase myDataBase;

    public NoteAdapter(Context context, List<NoteModel> noteModels) {

        this.context = context;
        this.noteModels = noteModels;

        myDataBase = new MyDataBase(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_list, viewGroup, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, final int position) {

        final NoteModel model = noteModels.get(position);

        noteViewHolder.txtTitle.setText(model.getTitle());
        noteViewHolder.txtDescription.setText((model.getDescription()));
        noteViewHolder.txtTime.setText(model.getCurrentTime());

        if (model.getRemember() == 0) {
            noteViewHolder.imgAlarm.setVisibility(View.GONE);
            noteViewHolder.txtTime.setVisibility(View.GONE);
        } else {
            noteViewHolder.imgAlarm.setVisibility(View.VISIBLE);
            noteViewHolder.txtTime.setVisibility(View.VISIBLE);
        }

        noteViewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_dialog_layout);

                final EditText edtTitle = (EditText) dialog.findViewById(R.id.edt_edit_dialog_title);
                final EditText edtDescription = (EditText) dialog.findViewById(R.id.edt_edit_dialog_description);

                Button btnUpdate = (Button) dialog.findViewById(R.id.btn_edit_dialog_update);
                Button btnCancel = (Button) dialog.findViewById(R.id.btn_edit_dialog_cancel);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myDataBase.updateRow(model.getId(),
                                edtTitle.getText().toString(),
                                edtDescription.getText().toString());

                        NoteModel noteModel = new NoteModel();

                        noteModel.setTitle(edtTitle.getText().toString());
                        noteModel.setDescription(edtDescription.getText().toString());
                        noteModel.setCurrentTime(model.getCurrentTime());


                        noteModels.remove(position);
                        noteModels.add(position, noteModel);

                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        noteViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_dialog);

                TextView txtYes = (TextView) dialog.findViewById(R.id.txt_dialog_yes);
                TextView txtNo = (TextView) dialog.findViewById(R.id.txt_dialog_no);

                txtYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myDataBase.deleteRow(model.getId()); //delete from database
                        noteModels.remove(model); // delete from list
//                        notifyDataSetChanged(); // refresh the list

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, noteModels.size());

                        dialog.dismiss();
                    }
                });

                txtNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.noteModels.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        TextView txtTime;
        ImageView imgEdit;
        ImageView imgDelete;
        ImageView imgAlarm;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_list_title);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_list_description);
            txtTime = (TextView) itemView.findViewById(R.id.txt_list_time);

            imgEdit = (ImageView) itemView.findViewById(R.id.img_list_edit);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_list_delete);

            imgAlarm = (ImageView) itemView.findViewById(R.id.img_list_time);
        }
    }
}
