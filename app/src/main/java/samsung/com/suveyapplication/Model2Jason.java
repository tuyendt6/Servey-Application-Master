package samsung.com.suveyapplication;

import com.google.gson.annotations.SerializedName;
import com.samsung.table.tblCorregimientos;
import com.samsung.table.tblDistritos;
import com.samsung.table.tblEncuestaDisenos;
import com.samsung.table.tblEncuestaPreguntas;
import com.samsung.table.tblEncuestaRespuestaGrupos;
import com.samsung.table.tblEncuestaRespuestas;
import com.samsung.table.tblFrecuenciaVisitas;
import com.samsung.table.tblProvincias;
import com.samsung.table.tblPuntosDeVenta;
import com.samsung.table.tblVendedores;
import com.samsung.table.tblVendedoresPorPuntosDeVenta;
import com.samsung.table.tblZonas;

/**
 * Created by SamSunger on 5/13/2015.
 */
public class Model2Jason {
    //1
    public class Vendedores {
        @SerializedName(tblVendedores.PK_ID)
        public String PK_ID;
        @SerializedName(tblVendedores.NOMBRE_COMPETO)
        public String NOMBRE_COMPETO;
        @SerializedName(tblVendedores.NOMBRE_USUARIO)
        public String NOMBRE_USUARIO;
        @SerializedName(tblVendedores.CLAVE)
        public String CLAVE;
        @SerializedName(tblVendedores.DERECCION)
        public String DERECCION;
        @SerializedName(tblVendedores.DOC_IDENT)
        public String DOC_IDENT;
        @SerializedName(tblVendedores.TELEFONOS)
        public String TELEFONOS;
        @SerializedName(tblVendedores.ACTIVO)
        public String ACTIVO;
        @SerializedName(tblVendedores.Email1)
        public String Email1;
        @SerializedName(tblVendedores.Email2)
        public String Email2;

    }

    //2
    public class PuntosDeVanta {
        @SerializedName(tblPuntosDeVenta.PK_ID)
        public String PK_ID;
        @SerializedName(tblPuntosDeVenta.FACT_DEFT_ID)
        public String FACT_DEFT_ID;
        @SerializedName(tblPuntosDeVenta.NOMBRE)
        public String NOMBRE;
        @SerializedName(tblPuntosDeVenta.DIRECCION)
        public String DIRECCION;
        @SerializedName(tblPuntosDeVenta.PROVINCIAID)
        public String PROVINCIAID;
        @SerializedName(tblPuntosDeVenta.DISTRITOID)
        public String DISTRITOID;
        @SerializedName(tblPuntosDeVenta.ZONAID)
        public String ZONAID;
        @SerializedName(tblPuntosDeVenta.DESCCION_ADICIONALES)
        public String DESCCION_ADICIONALES;
        @SerializedName(tblPuntosDeVenta.TELEFONO)
        public String TELEFONO;
        @SerializedName(tblPuntosDeVenta.ACTIVO)
        public String ACTIVO;
        @SerializedName(tblPuntosDeVenta.POSION_LAT)
        public String POSION_LAT;
        @SerializedName(tblPuntosDeVenta.POSION_LON)
        public String POSION_LON;
    }

    //3
    public class Zonas {
        @SerializedName(tblZonas.PK_ID)
        public String PK_ID;
        @SerializedName(tblZonas.NOMBRE)
        public String NOMBRE;
    }

    //4
    public class Distritos {
        @SerializedName(tblDistritos.PK_ID)
        public String PK_ID;
        @SerializedName(tblDistritos.PROVINCIA_ID)
        public String PROVINCIA_ID;
        @SerializedName(tblDistritos.NOMBRE)
        public String NOMBRE;
    }

    //5
    public class Provincias {
        @SerializedName(tblProvincias.PK_ID)
        public String PK_ID;
        @SerializedName(tblProvincias.NOMBRE)
        public String NOMBRE;
    }

    public String PREGUNTA_10;

    //6
    //Day la bang Servey Name :
    public class EncuestaDisenos {
        @SerializedName(tblEncuestaDisenos.PK_ID)
        public String PK_ID;
        @SerializedName(tblEncuestaDisenos.NOMBRE)
        public String NOMBRE;
        @SerializedName(tblEncuestaDisenos.DESCRIPCION)
        public String DESCRIPCION;
        @SerializedName(tblEncuestaDisenos.ACTIVO)
        public String ACTIVO;

    }

    // Day la bang Question cho Servey :
//7
    public class EncuestasPreguntas {
        @SerializedName(tblEncuestaPreguntas.PK_ID)
        public String PK_ID;
        @SerializedName(tblEncuestaPreguntas.DISENO_ID)
        public String DISENO_ID;
        @SerializedName(tblEncuestaPreguntas.POSICION_COLUMA)
        public String POSICION_COLUMA;
        @SerializedName(tblEncuestaPreguntas.NOMBRE_COLUMNA)
        public String NOMBRE_COLUMNA;
        @SerializedName(tblEncuestaPreguntas.TEXTO_PREGUNTA)
        public String TEXTO_PREGUNTA;
        @SerializedName(tblEncuestaPreguntas.Q_TYPE)
        public String Q_TYPE;
        @SerializedName(tblEncuestaPreguntas.GRUPO_RESPUESTAS_ID)
        public String GRUPO_RESPUESTAS_ID;
    }

    //8
    public class EncuestasRespuestasGrupos {
        @SerializedName(tblEncuestaRespuestaGrupos.PK_ID)
        public String PK_ID;
        @SerializedName(tblEncuestaRespuestaGrupos.NOMBRE)
        public String NOMBRE;
        @SerializedName(tblEncuestaRespuestaGrupos.DESCRIPCION)
        public String DESCRIPCION;
    }

    //9
    public class EncuestaRespuestas {
        @SerializedName(tblEncuestaRespuestas.PK_ID)
        public String PK_ID;
        @SerializedName(tblEncuestaRespuestas.CODIGO)
        public String CODIGO;
        @SerializedName(tblEncuestaRespuestas.DESCRIPCION)
        public String DESCRIPCION;
        @SerializedName(tblEncuestaRespuestas.GRUPO_RESQUEST_AS_ID)
        public String GRUPO_RESQUEST_AS_ID;
    }

    //10 table :
    public class FrecuenciaVisitas {
        @SerializedName(tblFrecuenciaVisitas.PK_ID)
        public String PK_ID;
        @SerializedName(tblFrecuenciaVisitas.CODIGO)
        public String CODIGO;
        @SerializedName(tblFrecuenciaVisitas.DESCRIPCION)
        public String DESCRIPCION;
    }

    //11
    public class VendedoresPorPuntosDeVenta {
        @SerializedName(tblVendedoresPorPuntosDeVenta.PK_ID)
        public String PK_ID;
        @SerializedName(tblVendedoresPorPuntosDeVenta.VENDEDOR_ID)
        public String VENDEDOR_ID;
        @SerializedName(tblVendedoresPorPuntosDeVenta.PDVID)
        public String PDVID;
        @SerializedName(tblVendedoresPorPuntosDeVenta.FRECUENCIA_VISITA_ID)
        public String FRECUENCIA_VISITA_ID;
    }

    //11
    public class Corregimientos {
        @SerializedName(tblCorregimientos.PK_ID)
        public String PK_ID;
        @SerializedName(tblCorregimientos.DISTRITOID)
        public String DISTRITOID;
        @SerializedName(tblCorregimientos.NOMBRE)
        public String NOMBRE;
    }

}
