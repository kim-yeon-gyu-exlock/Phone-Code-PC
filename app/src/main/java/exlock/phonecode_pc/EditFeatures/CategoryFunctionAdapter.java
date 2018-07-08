package exlock.phonecode_pc.EditFeatures;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import exlock.phonecode_pc.LanguageProfile;
import exlock.phonecode_pc.R;

class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.ViewHolder> {

    List<BlockLists> blocks = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView func1;
        private final TextView func2;
        private final EditText arg;
        private final View separator;
        private final RelativeLayout itemBlock;

        ViewHolder(final View v){
            super(v);
            func1 = v.findViewById(R.id.func1);
            func2 = v.findViewById(R.id.func2);
            arg = v.findViewById(R.id.argEditText);
            separator = v.findViewById(R.id.separator);
            itemBlock = v.findViewById(R.id.itemBlock);
        }
        TextView getFunc1() {
            return func1;
        }
        TextView getFunc2() {
            return func2;
        }
        EditText getArg() {
            return arg;
        }
        View getSeparator(){return separator;}
        RelativeLayout getItemBlock() {
            return itemBlock;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_block, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String funcString1 = blocks.get(position).func1;
        String arg = blocks.get(position).arg;
        String funcString2 = blocks.get(position).func2;

        holder.getItemBlock().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                createDialog(holder);
                return true;
            }
        });

        boolean isFunc1Empty = funcString1.equals("");
        holder.getItemBlock().setVisibility(isFunc1Empty ? View.GONE : View.VISIBLE);
        if(isFunc1Empty){
            return;
        }
        boolean isFunc2Empty = funcString2.equals("");
        holder.getArg().setVisibility(isFunc2Empty ? View.GONE : View.VISIBLE);
        holder.getFunc2().setVisibility(isFunc2Empty ? View.GONE : View.VISIBLE);
        holder.getFunc1().setText(funcString1);
        if(isFunc2Empty){
            return;
        }
        holder.getArg().setText(arg);
    }
    private void createDialog(ViewHolder holder){
        final String[] items = {"Remove Block", "Edit Block", "Add Block Below"};
        final Context context = holder.getItemBlock().getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, final int which) {
                        Toast.makeText(context, "You clicked "+items[which], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }
    @Override
    public int getItemCount() {
        return blocks.size();
    }
}