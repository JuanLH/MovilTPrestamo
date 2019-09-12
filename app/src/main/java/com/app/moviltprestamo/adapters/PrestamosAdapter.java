package com.app.moviltprestamo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.moviltprestamo.R;
import com.app.moviltprestamo.entities.PrestamoActivo;

import java.util.List;

public class PrestamosAdapter extends
        RecyclerView.Adapter<PrestamosAdapter.ViewHolder> {

    private List<PrestamoActivo> mPrestamos;

    // Pass in the contact array into the constructor
    public PrestamosAdapter(List<PrestamoActivo> prestamos) {
        mPrestamos = prestamos;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_prestamo_activo, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PrestamosAdapter.ViewHolder viewHolder, int position) {
        PrestamoActivo prestamo = mPrestamos.get(position);

        TextView txtPrestamoId = viewHolder.idPrestamo;
        txtPrestamoId.setText(prestamo.getId_prestamo()+"");

        TextView txtCliente = viewHolder.clientName;
        txtCliente.setText(prestamo.getNombre_cliente());

        TextView txtMonto = viewHolder.monto;
        txtMonto.setText(prestamo.getMonto_prestamo()+"");

        TextView txtPagos = viewHolder.pagos;
        txtPagos.setText(prestamo.getPagos()+"");

        TextView txtCuota = viewHolder.cuota;
        txtCuota.setText(prestamo.getMonto_pago()+"");

        TextView txtPendiente = viewHolder.pendiente;
        txtPendiente.setText(prestamo.getMonto_pendiente()+"");

        RelativeLayout view = viewHolder.view;
        view.setBackgroundColor((position % 2 == 0)?Color.rgb(217,160,8):Color.WHITE);
        //view.setBackgroundColor(Color.rgb(217,160,8));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPrestamos.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {


        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView idPrestamo;
        public TextView clientName;
        public TextView monto;
        public TextView pagos;
        public TextView cuota;
        public TextView pendiente;
        public RelativeLayout view;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            idPrestamo = (TextView) itemView.findViewById(R.id.id_prestamo);
            clientName = (TextView) itemView.findViewById(R.id.client_name);
            monto = (TextView) itemView.findViewById(R.id.monto);
            pagos = (TextView) itemView.findViewById(R.id.pagos);
            cuota = (TextView) itemView.findViewById(R.id.cuota);
            pendiente = (TextView) itemView.findViewById(R.id.pendiente);
            view = (RelativeLayout) itemView.findViewById(R.id.rel1);

        }
    }

}
