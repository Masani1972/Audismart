package com.aosas.audismart.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.net.URISyntaxException;
import java.security.MessageDigest;

import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.DocumentoIdentidad;
import com.aosas.audismart.model.Periodicidad;
import com.aosas.audismart.repository.FileAsserts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * The type Util.
 */
public class Util {


    /**
     * Jsonto array list array list.
     *  Convierte json en Arrays
     *
     * @param json   the json
     * @param object the object
     * @return the array list
     */
    public static ArrayList jsontoArrayList(String json, Object object) {
        if (object instanceof Departamento) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Departamento>>() {
            }.getType();
            ArrayList<Departamento> departamentos = gson.fromJson(json, type);

            return departamentos;
        } else if (object instanceof Ciudad) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Ciudad>>() {
            }.getType();
            ArrayList<Ciudad> ciudades = gson.fromJson(json, type);
            return ciudades;
        } else if (object instanceof Categoria) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Categoria>>() {
            }.getType();
            ArrayList<Categoria> categorias = gson.fromJson(json, type);

            return categorias;
        } else if (object instanceof DocumentoIdentidad) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<DocumentoIdentidad>>() {
            }.getType();
            ArrayList<DocumentoIdentidad> documentos = gson.fromJson(json, type);
            return documentos;

        } else if (object instanceof Periodicidad) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Periodicidad>>() {
            }.getType();
            ArrayList<Periodicidad> periodos = gson.fromJson(json, type);
            return periodos;
        }
        return null;
    }

    /**
     * Text to md 5 string.
     * Se obtiene un ND5 a partir de un texto
     * @param text the text
     * @return the string
     */
    public static String textToMD5(String text) {
        String original = text;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

    /**
     * Returns the consumer friendly device name
     *
     * @return the device name
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

    /**
     * String to date date.
     *
     * @param formato the formato
     * @param fecha   the fecha
     * @return the date
     */
    public static Date stringToDate(String formato, String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat(formato, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));
        try {
            Date date = (Date) formatter.parse(fecha);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Validate formulario relative boolean.
     *
     * @param layout_Form   the layout form
     * @param camposValidos the campos validos
     * @return the boolean
     */
    public static boolean validateFormularioRelative(RelativeLayout layout_Form, int camposValidos) {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = layout_Form.getChildAt(i);
            if (v instanceof EditText) {
                EditText tv = (EditText) v;
                if ((tv != null && tv.getText().toString().length() > 0))
                    editTextOk++;
                continue;
            }
        }

        if (editTextOk == camposValidos)
            return true;
        else
            return false;
    }

    /**
     * Validate formulario linear boolean.
     *
     * @param layout_Form the layout form
     * @return the boolean
     */
    public static boolean validateFormularioLinear(LinearLayout layout_Form) {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 1; i < childcount; i = i + 2) {
            View v = layout_Form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }

        if (editTextOk == childcount / 2)
            return true;
        else
            return false;
    }

    /**
     * Buscar ciudad string.
     *
     *     Buscar nombre ciudad
     *
     * @param context  the context
     * @param id       the id
     * @param idCiudad the id ciudad
     * @return the string
     */
    public static String buscarCiudad(Context context, String id, String idCiudad) {
        ArrayList<Ciudad> ciudad = Util.jsontoArrayList(FileAsserts.readJsonDescripcion(context, id), new Ciudad());
        for (int i = 0; i < ciudad.size(); i++) {
            if (ciudad.get(i).Id_ciudad.equals(idCiudad)) {
                return ciudad.get(i).Nombre;
            }
        }
        return "";
    }

    /**
     * Buscar departamento string.
     *
     *     Buscar nombre departamento
     * @param context the context
     * @param id      the id
     * @return the string
     */
    public static String buscarDepartamento(Context context, String id) {
        ArrayList<Departamento> departamentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(context, "departamentos"), new Departamento());
        for (int i = 0; i < departamentos.size(); i++) {
            if (departamentos.get(i).Id_departamento.equals(id)) {
                return departamentos.get(i).Nombre;
            }
        }
        return "";
    }

    /**
     * Buscar documento string.
     * Buscar nombre documento
     *
     * @param context the context
     * @param id      the id
     * @return the string
     */
    public static String buscarDocumento(Context context, String id) {
        ArrayList<DocumentoIdentidad> tipoDocumento = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(context, "documentos"), new DocumentoIdentidad());
        for (int i = 0; i < tipoDocumento.size(); i++) {
            if (tipoDocumento.get(i).id_Documento.equals(id)) {
                return tipoDocumento.get(i).nombre;
            }
        }
        return "";
    }

    /**
     * Buscar categoria string.
     *
     * Buscar nombre categoria
     * @param context the context
     * @param id      the id
     * @return the string
     */
    public static String buscarCategoria(Context context, String id) {
        ArrayList<Categoria> categorias = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(context, "categorias"), new Categoria());
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).id_Categoria.equals(id)) {
                return categorias.get(i).nombre_Categoria;
            }
        }
        return "";
    }

    /**
     * Buscar periodo string.
     *
     * Buscar nombre priodicidad
     * @param context the context
     * @param id      the id
     * @return the string
     */
    public static String buscarPeriodo(Context context, String id) {
        ArrayList<Periodicidad> prediodos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(context, "periodicidad"), new Periodicidad());
        for (int i = 0; i < prediodos.size(); i++) {
            if (prediodos.get(i).id.equals(id)) {
                return prediodos.get(i).nombre;
            }
        }
        return "";
    }


    /**
     * Gets path.
     *
     * @param context the context
     * @param uri     the uri
     * @return the path
     * @throws URISyntaxException the uri syntax exception
     */
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        String wholeID = DocumentsContract.getDocumentId(uri);
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);
        String filePath = "";
        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return (filePath);
    }
}
