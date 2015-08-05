package com.example.prog2.eurotravelplanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bernardo Colon on 9/7/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="DB_EuroTravel";
    private static final int DB_SCHEMA_VESION= 15;
    SQLiteDatabase db;

    public static final String TABLE_NAME = "Datos_Generales";
    public static final String ID_datos = "_id";
    public static final String ID_ciudad= "nombre_ciudad";
    public static final String CN_info= "info_general";
    public static final String CN_segundo_nombre= "otros_nombres";
    public static final String CN_lema= "lema";
    public static final String CN_clima= "clima";
    public static final String CN_moneda= "moneda";
    public static final String CN_demografia= "demografia";

    public static final String TABLE_NAME2 = "Tips";
    public static final String ID_tip = "_id";
    public static final String CN_tip= "Tip";
    public static final String ID_ciudad2= "nombre_ciudad";
    public static final String CN_tipo= "Tipo";

    public static final String TABLE_NAME3 = "Conteo_Tips";
    public static final String CN_trans= "Transporte";
    public static final String CN_hosp= "Hospedaje";
    public static final String CN_gas= "Gastronomia";
    public static final String CN_entre= "Entretenimiento";
    public static final String CN_lugar= "Lugares_Interes";
    public static final String CN_comp= "Compras";

    public static final String TABLE_NAME4 = "Informacion";
    public static final String ID_info = "_id";
    public static final String ID_ciudad3= "nombre_ciudad";
    public static final String CN_categoria= "Categoria";
    public static final String CN_sub_cat= "Sub_Categoria";
    public static final String CN_nombre= "Nombre";
    public static final String CN_dato1= "Dato1";
    public static final String CN_dato2= "Dato2";
    public static final String CN_dato3= "Dato3";

    public static String where = null; //para datos generales
    public static String where2 = null; //para tips
    public static String where3 = null; //para info

    // Query que creara tabla Datos Generales
    private static final String TABLE_CREATE="CREATE TABLE "+TABLE_NAME+" ("+ID_datos+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+ID_ciudad+" text not null, "+CN_info+" text not null, "+CN_segundo_nombre+" text ,"+CN_lema+" text ,"+
            " "+CN_clima+" text not null, "+CN_moneda+" text not null, "+CN_demografia+" text not null)";

    private static final String TABLE_CREATE2="CREATE TABLE "+TABLE_NAME2+" ("+ID_tip+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+CN_tip+" text not null, "+ID_ciudad2+" text not null, "+CN_tipo+" text not null)";

    private static final String TABLE_CREATE3="CREATE TABLE "+TABLE_NAME3+" ("+CN_trans+" Integer, "+
            " "+CN_hosp+" Integer, "+CN_gas+" Integer, "+CN_entre+" Integer,"+CN_lugar+" Integer ,"+
            " "+CN_comp+" Integer)";

    private static final String TABLE_CREATE4="CREATE TABLE "+TABLE_NAME4+" ("+ID_info+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+ID_ciudad3+" text not null, "+CN_categoria+" text not null, "+CN_sub_cat+" text,"+CN_nombre+" text not null,"+
            " "+CN_dato1+" text not null, "+CN_dato2+" text, "+CN_dato3+" text)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE2);
        db.execSQL(TABLE_CREATE3);
        db.execSQL(TABLE_CREATE4);


        InsertarDG(db);
        InsertarTips(db);

        InsertarGastronomia(db);
        InsertarHospedaje(db);
        InsertarLugarInteres(db);
        InsertarEntretenimiento(db);
        InsertarCompras(db);

        //db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
        //        "values('Si viajas desde otro continente, muchas aerolíneas ofrecen tiquetes más baratos hacia ciudades diferentes a París. Puede ser útil arribar primero a otra ciudad y luego llegar a la capital francesa en tren.', 'paris', 'transporte')");


        this.db = db;

    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VESION);

    }

    //Retorno de datos generales
    public Cursor getDatos(){
        String columnas[]={ID_datos,ID_ciudad,CN_info,CN_segundo_nombre,CN_lema,CN_clima,CN_moneda,CN_demografia};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME,columnas,where,null,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    //retorno de tips
    public Cursor getTips(){
        String columnas[]={CN_tip};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME2,columnas,where2,null,null,null,"RANDOM()");
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    //retorno de informacion
    public Cursor getInfo(){
        String columnas[]={ID_info,ID_ciudad3,CN_categoria,CN_sub_cat,CN_nombre,CN_dato1,CN_dato2,CN_dato3};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME4,columnas,where3,null,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        this.onCreate(db);
    }


     public void InsertarTips(SQLiteDatabase db){

        //Insertamos datos fijos de la tabla Tips
        //paris(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si viajas desde otro continente, muchas aerolíneas ofrecen tiquetes más baratos hacia ciudades diferentes a París. Puede ser útil arribar primero a otra ciudad y luego llegar a la capital francesa en tren.', 'paris', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En París, aprovecha la organización del transporte público, especialmente el metro. Un taxi en esta ciudad es sumamente costoso y el caos vehicular tampoco garantiza que llegues rápido al destino.', 'paris', 'transporte')");
        //paris(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('París, como toda ciudad turística, varía su oferta hotelera según la temporada del año. Durante todos los meses pueden encontrarse hostales baratos u hoteles señalados como Nouvelle Norme (NN), que significa que aún no están acreditados y por eso deben cobrar más barato.', 'paris', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Es buena idea tener las fechas exactas de tu estancia antes de empezar la búsqueda comparativa y elaborar el presupuesto, pues las habitaciones también varían su precio según el día de la semana.', 'paris', 'hospedaje')");
        //paris(gatronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Varios restaurantes en las grandes avenidas ofrecen menús para turistas, pero es mejor alejarse de las vías principales y adentrarse en los barrios. En las callejuelas encontrarás pequeños establecimientos, los cuales generalmente son atendidos por gente del lugar.', 'paris', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El pan es un producto emblemático de París. En las panaderías puedes comprar el tradicional baguette, barra larga y crujiente de pan; el pain, que es más suave; o el ficelle que es más fino y de textura más delicada.', 'paris', 'gastronomia')");
        //paris(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En Alcazar hay lunes líricos. En Bel Canto los camareros son alumnos de conservatorio y cantan entre las mesas. En el restaurante chino La Dame de Canton hay jazz en vivo.', 'paris', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Los músicos más famosos van de concierto a París. En la página Eventful pueden consultar la oferta de espectáculos musicales y organizar una buena agenda.', 'paris', 'entretenimiento')");
        //paris(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Ve temprano a la catedral de Notre Dame, pues el nivel de turistas es bajo a altas horas de la mañana. ¡A nadie le gusta madrugar en vacaciones!', 'paris', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Un buen recorrido podría partir por la avenida de los Campos Elíseos hasta la rotonda del Arco del Triunfo, lo que implica caminar buena parte de las tiendas de moda. El buen caminante puede seguir su recorrido hasta la Torre Eiffel.', 'paris', 'interes')");
        //paris(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Tras ver los precios en las estanterías o los tableros con menús costosos en cada calle, es fácil rendirse en la tarea de encontrar productos baratos. Recuerda siempre que fuera de las vías principales hallarán mejores precios.', 'paris', 'compras')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('¡No toques los productos! Pide autorización al vendedor antes de tomar las cosas.', 'paris', 'compras')");


        //roma(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Los semáforos son cruzados en color rojo, las motocicletas no respetan a los transeúntes y los pasos peatonales están de adorno.', 'roma', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Roma merece ser recorrida a pie. Por eso, es importante no desanimarnos por la inseguridad vial y, simplemente, tomar precauciones al respecto.', 'roma', 'transporte')");
        //roma(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Los hostales (“Hoteles baratos”) siempre son una fabulosa opción para ahorrar dinero, pero deben ser reservados con meses de anterioridad.', 'roma', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Roma es una de las ciudades más costosas para visitar. El rango de precios puede variar, pero una habitación en un hotel económico puede costar 80 euros por noche.', 'roma', 'hospedaje')");
        //roma(gastronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Piérdete en los barrios y entra a las pizzerías locales.', 'roma', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Sé específico con tu pedido y, en lo posible, señala claramente en el menú el plato que deseas. En varias ocasiones los meseros trataran de servirte platos más costosos que los que ordenes. ', 'roma', 'gastronomia')");
        //roma(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('¡La música! Es una idea grandiosa asistir a conciertos en Roma, donde la oferta de entretenimiento ofrece espectáculos en vivo de diversos géneros musicales.', 'roma', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El fútbol es un grandioso plan en Roma, pero debes asistir con la mayor precaución. Un partido de Roma o Lazio contra un equipo más chico es mucho más tranquilo que el tradicional derbi anual entre estos dos clubes.', 'roma', 'entretenimiento')");
        //roma(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Evita las interminables filas en el Coliseo Romano comprando tu tiquete de ingreso en una de las puertas del Foro Romano o el Palatino, ya que el mismo ticket te sirve para ingresar a todos los monumentos y puedes pasar de uno a otro caminando.', 'roma', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Un buen circuito a pie por la antigua roma puede iniciar en Il Vittoriano, monumento a Víctor Manuel II; seguir hasta la Piazza del Campidoglio, una increíble obra de Miguel Ángel; continuar por los Museos Capitolinos, los museos públicos más antiguos del mundo; después el Foro Romano, el Coliseo y el ', 'roma', 'interes')");
        //roma(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si tienes ganas de souvenires, huye de las vías principales y de las calles colindantes a los monumentos. ', 'roma', 'compras')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Cerca de las estaciones de trenes suele haber puestos de venta callejeros un poco más económicos.', 'roma', 'compras')");


        //londres(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si vas a visitar Londres como turista y quieres aprovechar al máximo tu viaje y ver todo lo que puedas, es muy interesante adquirir una tarjeta London Pass.', 'londres', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En general el sistema de transporte público de Londres es muy bueno. Aunque pueda aparecer caro a primera vista, el transporte público de Londres ofrece muchos descuentos para estudiantes, pensionistas, turistas etc.', 'londres', 'transporte')");
        //londres(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Londrés es muy grande y muy diversa y es importante planificar tu viaje. Debes tener claro dónde te vas a quedar al menos - a pesar de que el alojamiento es caro, hay muchas posibilidades de compartir piso con gente como tú.', 'londres', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Reservar el hotel con suficiente antelación: los hoteles en Londres son en general viejos, malos y carísimos. Cuanto más esperéis para reservar, mayor probabilidad de que os toque un hotel mal situado o incomodísimo.', 'londres', 'hospedaje')");
        //londres(gastronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En Camden Market, por ejemplo, podrás disfrutar de un sinfín de puestos de comida cocinada por nativos de decenas de países distintos.', 'londres', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Para ahorrar al máximo puedes comprar en algún supermercado o comer en pubs o restaurantes de comida rápida o buffet económico. En Londres hay parques maravillosos que son ideales para montar un picnic improvisado.', 'londres', 'gastronomia')");
        //londres(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Los visitantes pueden acudir a las plazas principales de la ciudad, como Hyde Park, el parque de Victoria, y Potters Field Park, donde pantallas gigantes serán establecidas para mostrar la cobertura en vivo de los eventos olímpicos, junto con música en vivo y entretenimiento gratis.', 'londres', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Londres es la capital mundial de la vida nocturna y de las tendencias en materia de clubes y discotecas. La noche en Londres requiere una buena planificación y tener las ideas muy claras. Si se hace bien se puede disfrutar de los mejores DJs y de la gente más guapa de la ciudad.', 'londres', 'entretenimiento')");
        //londres(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El internacionalmente famoso Museo Británico expone las obras de la humanidad desde la prehistoria hasta la época moderna en todo el planeta. Destacan la piedra Rosetta, las esculturas del Partenón y las momias de la colección del Antiguo Egipto. La entrada es gratuita.', 'londres', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Explora la Torre de Londres, uno de los edificios más famosos del mundo. ¡Descubre sus 900 años de historia como palacio real, prisión y lugar de ejecuciones, arsenal, sala de joyas y zoológico!', 'londres', 'entretenimiento')");
        //londres(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Londres está llena de famosas calles comerciales que casi han llegado al estatus de lugares de interés histórico en Inglaterra. Probablemente una de las más famosas sea la Calle Oxford en el West End.', 'londres', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Londres es famoso por sus mercadillos al aire libre por toda la ciudad. Camden Market es uno de los más populares.', 'londres', 'interes')");


        //venecia(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('La mejor opción para llegar a Venecia es el tren, a menos que tengas la cuenta bancaria de un magnate y prefieras el avión.', 'venecia', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('La mejor forma de conocer la ciudad es caminando. Empaca zapatos cómodos, que eso de ir a Venecia en tacones y creerte Kim Kardashian está mandado a recoger.', 'venecia', 'transporte')");
        //venecia(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Reserva el hotel con mucha antelación. Hay portales online que te permiten comparar precios y ver las fotografías del lugar.', 'venecia', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Mucha gente prefiere alojarse en Mestre, que está muy cerca de Venecia. Allí se encuentran alojamientos más económicos y alternativas fáciles de transporte.', 'venecia', 'hospedaje')");
        //venecia(gastronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Tras ahorrar varios días, es momento de cumplir algún capricho. Si visitas un restaurante a manteles, pide algún plato a base de pescado o mariscos, la especialidad de los venecianos.', 'venecia', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Compra tus alimentos en un supermercado y cómelos en una de las tantas plazas del lugar.', 'venecia', 'gastronomia')");
        //venecia(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En febrero se realiza el Carnaval de Venecia, reconocido internacionalmente e icónico por sus máscaras. Si vas, seguramente vivirás un espectáculo increíble, pero tendrás que sortear con la época de mayor ocupación hotelera.', 'venecia', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Haz un recorrido de día y luego repítelo en la noche. En tan sólo un par de horas, Venecia baja el telón y, al regreso, sorprende con un escenario totalmente diferente.', 'venecia', 'entretenimiento')");
        //venecia(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('No contrates excursiones. Sólo te quitarán dinero y te mostrarán con afán aquello que tú mismo puedes ver sin prisa.', 'venecia', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El Mercado de Rialto es un lugar pintoresco y muy popular. Vale la pena visitarlo y poner a prueba la capacidad de ahorro, pues seguramente se te antojará comprarlo todo.', 'venecia', 'interes')");
        //venecia(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Al descender de la estación de tren, la mayoría de viajeros están tan encantados que quieren comprar todos los souvenires de la ciudad, pero en esta zona están los puestos más caros y de menor calidad.', 'venecia', 'compras')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Otoño es la estación con mejor relación costo-beneficio. Claro, es una temporada muy fría, pero los precios son los mejores de todo el año.', 'venecia', 'compras')");


        //madrid(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El abono de transporte turístico de Madrid permite realizar un número ilimitado de desplazamientos en metro, autobuses y trenes de cercanías.', 'madrid', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('La Tarjeta Madrid Card ofrece el acceso gratuito a más de 50 de los principales museos y monumentos de Madrid.', 'madrid', 'transporte')");
        //madrid(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Al elegir hoteles, si te alejas del centro tendrás opciones económicas y no suele haber problema porque el metro llega a todos los lados.', 'madrid', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si te quieres dar un capricho Madrid cuenta con hoteles únicos como el Hotel de las Letras, dedicado a este arte, Dormirdcine, un hotel dedicado al cine o un hotel con plantas especialmente hechas por artistas como el Hotel Silken Puerta América Madrid.', 'madrid', 'hospedaje')");
        //madrid(gastronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si quieres ir a un lugar barato, siempre tendrás un 100 Montaditos, una Sureña o un The Good Burguer.', 'madrid', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Comida china es muy conocido un restaurante que hay en Plaza de España, el Zhou Yulong, que está situado bajando las escaleras del metro de Plaza de España, o para ir a un indio, Lavapiés es una buena elección.', 'madrid', 'gastronomia')");
        //madrid(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Ve a la puerta del sol y encontraras muchas opciones para la vida nocturna además de muchas tiendas de importante nombre, el transporte subterráneo es la mejor opción.', 'madrid', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Aunque esta ciudad tiene ambiente todos los días de la semana, los domingos son bastante especiales (el rastro y día de toros o fútbol), es por ello que es muy recomendable hacer coincidir el viaje con el fin de semana.', 'madrid', 'entretenimiento')");
        //madrid(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Al tener tanta oferta cultural también pública, Madrid tiene planes lowcost que podrás aprovechar en tu visita a la ciudad. Hay centros culturales como el de la FUNDACIÓN MAPFRE, la Casa Encendida, algunas exposiciones de Caixa Forum o la Sala Canal de Isabel II que ofrecen muestras de arte gratuitas.', 'madrid', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El Museo del Prado es gratuito los días domingos después del mediodía, así que si quieres ahorrarte 14 euros programa tu visita para éste día. Las filas son un poco largas, pero también fluyen muy rápido.', 'madrid', 'interes')");
        //madrid(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Ir de compras en Madrid es mucho más divertido porque ofrece una amplia variedad de estilos diferentes para elegir.', 'madrid', 'compras')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Son cinco las áreas a las que puedes dirigirte si sales de compras en Madrid: Salamanca, Chueca, el centro (cerca de Puerta del Sol), Princesa y Cuatro Caminos.', 'madrid', 'compras')");


        //berlin(transporte)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El precio de alquilar una bicicleta en Berlín para un día oscila entre 10 y 12 euros, y son muchos los visitantes que optan por este medio de transporte para desplazarse por las zonas más turística de la ciudad.', 'berlin', 'transporte')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Es muy aconsejable que compres una tarjeta de transportes para moverte por Berlín.', 'berlin', 'transporte')");
        //berlin(hospedaje)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Una de las mejores zonas a la hora de buscar un hotel en Berlín son los alrededores de la Potsdamer Platz, una zona animada pero muy tranquila y segura.', 'berlin', 'hospedaje')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si planeas el viaje con tiempo, reservando el hotel con la suficiente antelación, puedes encontrar habitaciones dobles en hoteles bien situados a partir de 50 euros la noche.', 'berlin', 'hospedaje')");
        //berlin(gastronomia)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En busca de los platos tradicionales se recomienda ir a bares locales de cervezas donde además de diferentes especies de cerveza.', 'berlin', 'gastronomia')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Los turistas a menudo prefieren las albóndigas Klops y también chuletas preparadas con una receta especial llamadas Kotelett.', 'berlin', 'gastronomia')");
        //berlin(entretenimiento)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Muchas plazas y calles de la ciudad son lugares ideales para salir ya que los locales se suceden uno tras de otro.', 'berlin', 'entretenimiento')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Si busca vida y marcha nocturna la va a encontrar en casi todas las partes, incluso en el siempre joven barrio de Kreuzberg.', 'berlin', 'entretenimiento')");
        //berlin(lugares de interes)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('El Muro que separó el este y el oeste de la ciudad desde 1961 hatga el 9 de noviembre de 1989 debe ser otro de los protagonistas de tu visita, con puntos destacados como el Checkpoint Charlie o la East Side Gallery.', 'berlin', 'interes')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En cuanto a las zonas a visitar en Berlín, el gran eje será el que une la Columna de la Victoria en el gran parque Tiergarten, junto al que está la sede del Parlamente alemán, el Reichstag, pasando por la Puerta de Brandenburgo.', 'berlin', 'interes')");
        //berlin(compras)
        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('En una lista de los más importantes mercadillos en Berlín, deberemos diferenciar los Trödelmarkt y Flohmarkt, mercados de pulgas o mercadillos propiamente dichos, de los Antikmarkt, mercados de antigüedades.', 'berlin', 'compras')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
                "values('Las tiendas en Berlín se encuentran casi en cada calle, tanto los boutiques como pequeñas tiendas de souvenirs.', 'berlin', 'compras')");
    }

     public void InsertarDG(SQLiteDatabase db){
         //Insertamos datos fijos de la tabla Datos Generales
         db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                 "values('paris', 'La ciudad es el destino turístico más popular del mundo, con más de 42 millones de visitantes extranjeros por año.', 'La ciudad Luz', 'Fluctuat Nec Mergitur, Navega, sin ser nunca sumergido', 'París posee un clima continental caracterizado por veranos calurosos y sofocantes e inviernos fríos', 'Euro', 'París es el centro de un área metropolitana con 12,292,895 habitantes, la primera de la Unión Europea.')");

         db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                 "values('londres', 'Es la capital de Inglaterra y del Reino Unido, y la mayor ciudad y área urbana de Gran Bretaña y de toda la Unión Europea', '“SEÑOR, DIRÍGENOS”', 'El tiempo en Londres varía constantemente durante el día y, aunque no suele haber temperaturas extremas, en una misma jornada es habitual ver nubes, lluvia, frío e incluso sol y calor.', 'Libra Esterlina (GBP)', '8,615,246 hab')");

         db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                 "values('madrid', 'Es una ciudad cosmopolita que combina las infraestructuras más modernas y su condición de centro económico, financiero, administrativo y de servicios con un inmenso patrimonio cultural y artístico, legado de siglos de historia apasionante.', ' Villa y Corte, La Villa, La Capital del Reino', '«Fui sobre agua edificada, mis muros de fuego son. Esta es mi insignia y blasón»', 'De Madrid te sorprenderán sus cielos azules, intensos y rotundos. Con un clima seco y sin demasiadas precipitaciones a lo largo del año, los veranos son calurosos y los inviernos fríos.', 'Euro', '3,165,235 hab ')");

         db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+",  "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                 "values('venecia', 'Venecia es un conjunto de 120 islas unidas a través de puentes. Desde Mestre se puede llegar a Venecia utilizando el Puente de la Libertad, que lleva hasta la Piazzale Roma.', 'La Serenissima, La Ciudad de los canales', 'Dependiendo de la época en la que viajes, será un factor determinante para la planificación del viaje.', 'Euro', '270,884 hab')");

         db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                 "values('roma', 'Es la ciudad con la más alta concentración de bienes históricos y arquitectónicos del mundo su centro histórico delimitado por el perímetro que marcan las murallas aurelianas, superposición de huellas de tres milenios, es la expresión del patrimonio histórico, artístico y cultural del mundo occidental europeo.', 'La ciudad eterna', '«Senatus Populusque Romanus», El Senado y el Pueblo de Roma', 'El clima de Roma es mediterráneo y suave, lo que hace que cualquier época sea buena para conocer la ciudad.', 'Euro', '2,874,038 hab')");

         db.execSQL("INSERT INTO " + TABLE_NAME + " (" + ID_ciudad + ", " + CN_info + ", " + CN_clima + ", " + CN_moneda + ", " + CN_demografia + ") " +
                 "values('berlin', 'Es una ciudad mundial y un centro cultural y artístico de primer nivel. Es una de las ciudades más influyentes en el ámbito político de la Unión Europea y en 2006 fue elegida Ciudad Creativa por la Unesco.', 'Generalmente, junio, julio y agosto son los meses más cálidos, aunque no podemos hablar de un calor sofocante en ningún momento del año. Durante el verano hay una temperatura media de 24°C, llegándose a alcanzar máximas de 30ºC', 'Euro', '3,421,829 hab.')");
     }

     public void InsertarGastronomia(SQLiteDatabase db){

         //paris(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Epicure', '112 rue du Faubourg Saint-Honore, 75008 París, Francia', '+33 1 53 43 43 40','7:00 – 10:30/ 12:00 – 14:00/ 19:00 – 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Seb’on', '62 rue d’Orsel, 85018 París, Francia', '+33 1 42 59 74 32','Mié – sáb 19:00 – 23:00 / Sáb – dom 12:00 – 15:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Roomies', '14 Rue du Cygne, 75001 París, Francia', '+33 1 42 60 30 11','12:00 - 14:30 / 19:30 - 22:30 ')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Pur’ – Jean-Francois Rouquette', 'Rue de la Paix | Park Hyatt Paris, 75002 París, Francia', '+33 1 58 71 10 60','dom - sáb 20:00 - 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'L’Assommoir', '37 Rue Rodier, 75009 París, Francia', '+33 01 49 26 43 47','12:00 - 16:00 / 12:00 - 22:30 ')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Bistrot Chez France', '9 Rue Amelie, 75007 Paris, Francia', '+33 1 45 51 50 08','lun - vie 12:00 - 14:30 / sáb - dom 19:00 - 22:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'restaurantes', 'Cobea', '11, rue Raymond Losserand, 75014 Paris, Francia', '+33 1 43 20 21 39','Mar – Sab 12:15 – 13:30 / 19:15 – 21:30')");

        //paris(pastelerias)
        //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'pasteleria', 'Pierre Herme', ' 4, rue Cambon, 75001 Paris, Francia', '+33 1 43 54 47 77','10:00 – 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'pasteleria', 'Le Saotico', '96 Rue de Richelieu, 75002 Paris, Francia', '+33 1 42 96 03 20','Lun- Vie: 8:30 – 23:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'pasteleria', 'Bertie’s CupCakery', '26 Rue Chanoinesse, 75004 Paris, Francia', '+33 1 43 29 18 70','Lun- Dom: 11:00 – 19:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'pasteleria', 'Patisserie Stohrer', '51 rue Monotrgueil | 2eme arrondissement, 75002 Paris, Francia', '+33 01 42 33 38 20','Dom – Sab: 7:30 – 20:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'pasteleria', 'Ble Sucre', '7 rue Antoine Vollon, 75012 Paris, Francia', '+33 1 43 40 77 73','Mar – Sab 12:15 – 13:30')");


         //paris(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'comida_rapida', 'Cojean Sandwiches', '6 Rue de Seze, 75009 Paris, France', '+33 1 40 06 08 80','8:30 – 18:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'comida_rapida', 'L’ Entredgeu', '83 Rue Laugier, 75017 Paris, France', '+33 1 40 54 97 24','12:00 – 14:00, 19:30 – 22:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'comida_rapida', 'Café des Musees', '49 rue de Turenne, 75003 Paris, Francia', '+33 1 42 72 96 17','12:00 – 15:00 , 19:00 – 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'comida_rapida', 'Pret A Manger', '57 Rue des Petits Champs, 75001 Paris, Francia', '+33 1 42 61 67 56','7:30 – 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'gastronomia', 'comida_rapida', 'Vandermeersch', '278 rue Daumesnil, Paris, Francia', '+33 1 43 47 21 66','7:00 – 20:00')");


         //paris(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('paris', 'gastronomia', 'comida_tipica', 'Coq au vin', 'Exquisito platillo a base de pollo y vegetales sazonado con vino y finas especias.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('paris', 'gastronomia', 'comida_tipica', 'Canard a l’ orange', 'Platillo típico a base de pato, asado a fuego lento y bañado en una delicada salsa de naranja.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('paris', 'gastronomia', 'comida_tipica', 'Ratatouille', 'Deliciosa mezcla de vegetales sazonados y freídos con aceite de oliva y hierbas.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('paris', 'gastronomia', 'comida_tipica', 'Soupe a l’oignon', 'Platillo a base de cebolla con mantequilla y queso gratinado que se sirve con crujientes rebanadas de pan.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('paris', 'gastronomia', 'comida_tipica', 'Escargo', 'Plato de caracoles de tierra cocida , por lo general se sirve como aperitivo en Portugal , España y en Francia. ')");

         //londres(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'restaurantes', 'The Five Fields', '8-9 Blacklands Terrace, Londres SW3 2SP, Inglaterra', '+44 20 7838 1082','mar – sab 18:30: 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'restaurantes', 'Canvas', '1 Wilbraham Place | 1 Wilbraham Place, Londres SW1X 9AE, Inglaterra', '+44 20 7823 4463','mar – sáb 17:30 – 0:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'restaurantes', 'Restaurant Gordon Ramsay', 'Royal Hospital Road 68, Londres SW3 4HP, Inglaterra', '+44 020 7352 4441','12:00 – 14:30       18:30 – 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'restaurantes', 'Le Gavroche', '43 Upper Brook St. | Marble Arch, Londres W1k 7QR, Inglaterra', '+44 20 7408 0881','12:00 – 14:00    18:00 – 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'restaurantes', 'The Ledbury', '127 Ledbury Rd, Londres W11 2AQ, Inglaterra', '+44 20 7792 9090','12:00 – 14:30   18:30 – 22:45')");

         //londres(pastelerias)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'pasteleria', 'Ben’s Cookies', '355-361 Oxford Street, Londres, Inglaterra', '0203 206 2008','9:00 – 21:00 ')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'pasteleria', 'Palm Court at The Langham', '1c Portland Place, Regent Street | The Langham Hotel, Londres W1B 1JA, Inglaterra', '+44 20 7965 0195','6:30 – 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'pasteleria', 'Exmouth Coffee company', '83 Whitechapel High Street, Londres, Inglaterra', '+44 20 7377 1010','7:00 – 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'pasteleria', 'Brick Lane Beigel Bakery', '159 Brick Lane, Londres E1 6SB, Inglaterra', '+44 20 7729 0616','24/7 ')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'pasteleria', 'Juice & Public', '9 Wardour Street, Londres W1D 6PB, Inglaterra', '+44 20 7734 9541','8:00 - 21:00')");

         //londres(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'comida_rapida', 'Cafeteria Monmouth', '27 Monmouth Street, Londres WC2H 9EU', '+44 20 7232 3010','8:30 - 17:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'comida_rapida', 'Little Frankie’s', '7 Whitehall, Londres SW1A 2DD, Inglaterra, Londres', '+44 0207 389 3880','9:00 - 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'comida_rapida', 'Caffe Crema', '75 West Yard, Camden Lock, Londres', '+44 0871 4263876','9:30 - 18:30  / 19:30 - 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'comida_rapida', 'Clarence', '53 Whitehall | Whitehall, Londres SW1A 2HP', '+44 20 7930 4808','11:00 - 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'gastronomia', 'comida_rapida', 'Apostrophe', '23 Barrett Street, Londres W1U 1BF, Inglaterra, Londres', '+44 023 7355 1001','6:45 - 18:00')");

         //londres(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('londres', 'gastronomia', 'comida_tipica', 'Fish & Chips', 'Platillo hecho a base de pescado frito acompañado con papas.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('londres', 'gastronomia', 'comida_tipica', 'Roast beef', 'Asado de carne que se sirve con una guarnición de papas o verduras')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('londres', 'gastronomia', 'comida_tipica', 'Yorkshire Pudding', 'Tipo de pan horneado que acompaña  platillos a base de carne.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('londres', 'gastronomia', 'comida_tipica', 'Wellington beef', 'Lomo de ternera envuelto en hojaldre y cocinado al horno.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('londres', 'gastronomia', 'comida_tipica', 'Afternoon tea', 'El típico té de la tarde que consta de diferentes hierbas que suelen acompañarse con leche o crema.')");

         //roma(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'restaurantes', 'I Vicini Bistrot', 'Via di Torre Argentina 70, 00186 Roma', '+39 366 878 2625','13:00 - 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'restaurantes', 'La porta del principe', 'Via Portuense, 1585, 00148 Roma, Italia', '+39 066 500 1249','12:00 - 17:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'restaurantes', 'Il Tamburello di Pulcinella', 'Via Pasquale Fiore 23 | Metro a Cornelia/Baldo Degli Ubaldi, 00165 Roma', '+39 329 285 1794','18:00 - 0:00')");

         //roma(pastelerias)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'pasteleria', 'Biscottificio Innocenti', 'Via della Luce 21, 00153 Roma', '0039 06 5703926','8:00 - 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'pasteleria', 'Opulenti', 'Via Ascoli Piceno 44, Pigneto, 00176 Roma', '+39 339 725 0418','12:00PM - 1:00AM')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'pasteleria', 'Panzerotti & Friends', 'Via Appia Nuova 559/D, 00179 Roma', '+39 06 4549 2604','8:30 - 19:00')");

         //roma(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'comida_rapida', 'Madame Baguette', 'Via Boncompagni, 81, 00187 Roma', '+39 06 4201 3072','8:00 – 16:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'comida_rapida', 'Bacio di Puglia', 'Via del Mascherino, 59, 00193 Roma', '+39 06 6830 7697','9:00 – 21:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'gastronomia', 'comida_rapida', 'LasaGnaM', 'Via Nazionale, 184, 00184, Roma', '+39 06 4891 3677','7:30 – 23:00')");

         //roma(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('roma', 'gastronomia', 'comida_tipica', 'Bruschetta', 'Rebanadas de pan tostado aderezados con ajo y se sirven con aceite de oliva, jitomate y queso')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('roma', 'gastronomia', 'comida_tipica', 'Bucatini all’ Amatriciana', 'Tradicional pasta con tocino y jitomate')");


         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('roma', 'gastronomia', 'comida_tipica', 'Panini', 'Delicioso sándwich de varios ingredientes que se puede servir frio o caliente.')");


         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('roma', 'gastronomia', 'comida_tipica', 'Tarfuto negro', 'Exquisito pastel de chocolate muy típico de la región.')");


         //venecia(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'restaurantes', 'La Zucca', 'Santa Croce, 1762, Venezia', '+39 041 524 1570','12:30 – 14:30 , 19:00 – 22:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'restaurantes', 'Al Covo', 'Castello, 3968 30122, Venezia', '+39 041 522 3812','12:45 – 14:00 , 19:30 – 22:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'restaurantes', 'Bistrot De Venise', 'Calle dei Fabbri, Sestiere San Marco, 4685, 30124 Venezia', ' +39 041 523 6651','12:00 – 15:00 , 19:00- 00:00')");


         //venecia(pastelerias)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'pasteleria', 'Nobile Pasticceria in Venezia', 'Sestiere Cannaregio, 1818, 30121 Venecia', '+39 04 172 0731','6:30 – 20:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'pasteleria', 'Rosa Salva', 'San Marco, 950, 30124 Venecia', '+39 041 521 0544','8:00 – 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'pasteleria', 'Pasticceria Tonolo', 'Dorsoduro, 3764, 30123 Venecia', '+39 041 523 7209','7:35 – 20:00')");

         //venecia(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'comida_rapida', 'Q Food & More', 'San Marco 5464, 30124 Venecia', '+39 041 296 0057','10:30 – 21:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'comida_rapida', 'Tiziano Snack Bar', '5747 Cannaregio, Venecia', '+39 041 275 0071','8:00 – 20:00')");


         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'gastronomia', 'comida_rapida', 'Dal Moro’s – Fresh Pasta to go', 'Calle de la Casseleria, 5324 | Castello, 30122 Venecia', '+39 327 870 5014','15:00 – 20:30')");

         //venecia(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('venecia', 'gastronomia', 'comida_tipica', 'Brioche', 'Delicioso pan dulce o salado ideal para desayunar acompañado de un rico café espresso.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('venecia', 'gastronomia', 'comida_tipica', 'Risotto risi e bisi', 'Exquisito platillo típico de Venecia elaborado con arroz, jamon curado o serrano y chicharos.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('venecia', 'gastronomia', 'comida_tipica', 'Fegatto alla Veneziana', 'Higado guisado con cebolla dulce caramelizada.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('venecia', 'gastronomia', 'comida_tipica', 'Pez San Pedro', 'Platillo elaborado con pescado de fondo marino y cocido con vino blanco, sal, pimienta y aceite de oliva.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('venecia', 'gastronomia', 'comida_tipica', 'Campari', 'Licor elaborado a base de distintas hierbas, ruibardo y naranja amarga, es de color rojo y sabor amargo.')");

         //madrid(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'restaurantes', 'Rubaiyat', 'Juan Ramon Jimenez, 37, 28036 Madrid', '+34 913 59 56 96','13:00 – 16:00 , 20:30 – 24:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'restaurantes', 'Santceloni', 'Paseo de la Castellana, 57, 28046 Madrid', '+34 912 10 88 40','14:00 – 15:30 , 21:00 – 22:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'restaurantes', 'La Bola Taberna', 'Calle Bola, 5, 28013 Madrid', '+34 915 47 69 30','13:30 – 17:30')");

         //madrid(pastelerias)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'pasteleria', 'Martina Cocina', 'Plaza de Cascorro 11, 28005 Madrid', '+34 910 83 43 80','10:00 – 23:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'pasteleria', 'Celicioso', 'Calle Hortaleza, 3 28004 Madrid', '+34 915 31 88 87','12:00 – 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'pasteleria', 'La Mallorquina', 'Calle Mayor, 2,  Puerta del Sol, 28013 Madrid', '+34 915 21 12 01','9:00 – 21:15')");

         //madrid(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'comida_rapida', 'Casa Alberto', 'Calle de las Huertas, 18, 28012 Madrid', '+34 914 29 93 56','12:00 – 1:30')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'comida_rapida', 'Malaspina', 'Calle de Cadiz, 9, 28012 Madrid', '+34 915 23 40 24','11:00 – 2:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'gastronomia', 'comida_rapida', 'Stop Madrid', 'Calle de Hortaleza, 11, 28004 Madrid', '+34 915 21 88 87','12:30 – 2:00')");


         //madrid(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('madrid', 'gastronomia', 'comida_tipica', 'Ensalada de San Isidro', 'Deliciosa ensalada preparada con lechuga, aceitunas negras, huevo duro rebanado y atun.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('madrid', 'gastronomia', 'comida_tipica', 'Cocido madrileño', 'Exquisito estofado de vegetales, garbanzos con carne y otros embutidos')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('madrid', 'gastronomia', 'comida_tipica', 'Tapas', 'Tradicional aperitivo en la region que se sirve comunmente con bebidas alcoholicas y que se puede preparar con carnes, embutidos, quesos, pescados y mariscos')");


         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('madrid', 'gastronomia', 'comida_tipica', 'Bartolillos', 'Finas empanadilla rellena de crema con aroma de limon')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('madrid', 'gastronomia', 'comida_tipica', 'Leche merengada', 'Refrescante bebida tradicional que se prepara a base de leche, clara de huevo y canela. Se sirve frio.')");

         //berlin(restaurantes)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'restaurantes', 'Grill Royal ', 'Friedrichstraße 105b, 10117 Berlin', '+49 30 28879288','18:00 – 3:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'restaurantes', 'Hofbräu Berlin', 'Karl-Liebknecht-Straße 30, 10178 Berlin', '+49 30 679665520','10:00 – 1:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'restaurantes', 'Zur Letzten Instanz', 'Waisentraße 14-16, 10179 Berlin', '+49 30 24 25528','12:00 – 1:00')");

         //berlin(pastelerias)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'pasteleria', 'Mandragoras', 'Waller-Benjamin Platz 8 ,10629 Berlin', '+49 30 81723987','11:45 – 23:45')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'pasteleria', 'Tigertortchen', 'Spandauer Strasse 25, 10178 Berlin', '+49 30 67969051','11:00 – 18:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'pasteleria', 'Zeit fuer Brot', 'Alte Schoenhauser Str. 4, 10119 Berlin', '+49 30 28046780','8:00 – 18:00')");

         //berlin(comida rapida)
         //dato 1: direccion dato2: telefono dato3: horario

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'comida_rapida', 'Burgermeister', 'Oberbaumstraße 8, 10997 Berlín', '+49 30 23883840','11:00 – 15:00 ')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'comida_rapida', 'Curry Baude', 'Badstraße 1, 13357 Berlín', '+49 30 4941414','9:00  - 20:00')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'gastronomia', 'comida_rapida', 'Vapiano', 'Mittelstraße 51, 10117 Berlín', '+49 30 50154100','10:00 – 00:00')");

         //berlin(comida tipica)
         //dato1: informacion del plato

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('berlin', 'gastronomia', 'comida_tipica', 'Currywurst', 'Salchicha asada servida en rodajas acompañada de una salsa de curry y kétchup con papas fritas')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('berlin', 'gastronomia', 'comida_tipica', 'Kartoffelsalat', 'Ensalada de papas con verduras y mostaza.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('berlin', 'gastronomia', 'comida_tipica', 'Rote Grutze', 'Pasta de frutos rojos servida con leche, nata o helado de vainilla.')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+") " +
                 "values('berlin', 'gastronomia', 'comida_tipica', 'Eisbein mit Sauerkraut', 'Codillo de cerdo en salmuera, acompañado de ensalada de col y puré de chicharos. ')");

     }

     public void InsertarHospedaje(SQLiteDatabase db){
         //paris(hoteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hoteles', 'Le Bristol Paris', '112 rue du Faubourg Saint Honore | 8th arr., 76008 Paris', '011 33 1 82 88 84 89','5.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hoteles', 'Four Seasons Hotel George V ', '31 Av George V | 8th Arr., 75008 Paris', '011 33 1 49 52 71 70','5.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hoteles', 'Saint James Paris – Relais et Chateaux', '43 avenue Bugeaud, 75116 Paris', '011 33 9 75 18 26 37','4.0 de 5')");

         //paris(hosteles)
         //dato 1: direccion dato2: telefono dato3: rating
         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hosteles', 'Avalon Paris Hotel', '131/133 boulevard Magenta, 75010 Paris', '+33 1 42 85 50 00','3.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hosteles', 'Hotel Rocroy', '13 rue de Rocroy, 75010 Paris', '+33 1 49 70 70 70','3.6 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'hosteles', 'Hotel Boissiere', '53, rue Jean Jaures, 92300 Levallois-Perret', '011 33 1 82 88 85 29','3.6 de 5')");

         //paris(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating
         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'lugar_acampar', 'Camping Indigo Paris – Bois de Boulogne', '2 allee du Bord de l’Eau 75016 Paris', '+33 01 45 24 30 00','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'lugar_acampar', 'Camping International de Maisons – Laffitte', '1 rue Johnson 78600 Maisons-Laffitte', '+33 01 39 12 21 91','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'hospedaje', 'lugar_acampar', 'Huttopia Versailles', '31 rue Berthelot 78000 Versailles', '+33 01 39 51 23 61','3.0 de 5')");


         //roma(hoteles)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hoteles', 'Campo De’ Fiori', 'Via del Biscione, 6, 00186 Roma', '011 39 06 9480 4365','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hoteles', 'Hotel Diocleziano', 'Via Gaeta 71, 00185 Roma', '011 39 06 4890 0767','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hoteles', 'Hearth Hotel', 'Via Santamaura 2 | musei Vaticano, 00192 Roma', '+39 06 3903 8383','4.5 de 5')");


         //roma(hosteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hosteles', 'Villa Teresa', 'Via di Castel Giubileo, 5, 00138 Roma', '+39 06 880 5612','1.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hosteles', 'Rhona’s Rooms B&B', 'Via del Condotti, 23, 00187 Roma', '+39 06 6992 1006','1.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'hosteles', 'Motel Salaria', 'Via Salaria 1256, 00138 Roma', '+39 06 888 9656','3.0 de 5')");

         //roma(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating
         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'lugar_acampar', 'Camping Internazuionale di Castelfusano', 'Via Litoranea, 132, 00122 LIdo di Ostia Roma', '+39 06 562 3304','3.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'lugar_acampar', 'Camping Fabulous', 'Via di Malafede 225, 00125 Roma', '+39 06 525 9354','3.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'hospedaje', 'lugar_acampar', 'Camper club Miralago ', 'Via di Lunghezzina 75| Lunghezza, 00132 Roma', '+39 335 71 43 109','2.9 de 5')");

         //londres(hoteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hoteles', 'Conrad London St. James', '22-28 Broadway, Londres SW1H 0BH, Inglaterra', '011 44 20 3301 8080','5.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hoteles', 'Hotel 41', '41 Buckingham Palace Road, Londres Sw1W 0PS', '011 44 20 8040 1141','5.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hoteles', 'The Milestone Hotel', '1 Kensington Court | Kensington, Londres W8 5DL', '011 44 28 2003 2235','5.0 de 5')");

         //londres(hosteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hosteles', 'Clink Hostel', '78 King’s Cross Rd, London WC1X', '+44 20 7183 9400','3.6 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hosteles', 'Smart Russell Square Hostel', '70-72 Guilford St, London WC1N, Londres', '+44 20 7833 8818','3.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'hosteles', 'Palmer Lodge Swiss Cottage', '40 College Cres, London NW3 5LB', '+44 20 7483 8470','4.3 de 5')");

         //paris(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'lugar_acampar', 'Abbey Wood Caravan Club Site', 'Federation Road | Londres SE2 0LS', '+44 20 8311 7708','4.2 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'lugar_acampar', 'Crystal Palace Caravan Club Site', 'Crystal Palace Parade, Londres SE19 1UF', '+44 20 8778 7155','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'hospedaje', 'lugar_acampar', 'Lee Valley Camping and Caravan Park, Edmonton', 'Meridian Way | Edmonton, Londres N9 0AR', '+44 20 8803 6900','3.8 de 5')");

         //venecia(hoteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hoteles', 'Hilton Molino Stucky Venice', 'Giudecca, 810, 30133 Venezia', '+39 041 272 3311','4.4 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hoteles', 'The Westin Europa & Regina', 'S. Marco, 2159, 30124 Venezia', '+39 041 240 0001','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hoteles', 'Carnival Palace', 'Fondamenta di Cannaregio, 929, 30121', '+39 041 244 0320','4.5 de 5')");

         //venecia(hosteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hosteles', 'Yha Ostello', 'Fondamenta delle Zitelle, 86, 30133', '+39 041 523 5689','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hosteles', 'Ostello Santa Fosca', 'Fondamenta Canal Cannaregio, 2372', '+39 041 715775','3.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'hosteles', 'L’Imbarcadero', 'Santa Croce, Calle Zen, 1268, 30135', '+39 392 341 0861','4.6 de 5')");

         //venecia(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'lugar_acampar', 'Camping Serenissima', 'Via Padana 334/a | Malcontenta – Venezia, 30176 Malcontenta', '+39 041 921850','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'hospedaje', 'lugar_acampar', 'Camping Fusina', 'Via Moranzani 93, 30030 Venecia', '+39 041 547 0055','3.6 de 5')");

         //madrid(hoteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'hospedaje', 'hoteles', 'Jardines de Sabatini', 'Cuesta de San Vicente 16, 28008 Madrid', '011 34 911 98 32 90','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'hospedaje', 'hoteles', 'The Westin Palace Madrid', 'Plaza de las Cortes 7, 28014 Madrid', '+34 913 60 80 00','4.3 de 5')");

         //madrid(hosteles)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'hospedaje', 'hosteles', 'Way Hostel', 'Calle Relatores, 17, 28012 Madrid', '+34 914 20 05 83','4.5 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'hospedaje', 'hosteles', 'U Hostels', 'Calle de Sagasta, 22, 28004 Madrid', '+34 914 45 03 00','4.6 de 5')");

         //madrid(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'hospedaje', 'lugar_acampar', 'Only You Hotel & Lounge', 'Calle Barquillo 21, 28004 Madrid', '+34 910 05 22 22','4.7 de 5')");

        //berlin(hoteles)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'hospedaje', 'hoteles', 'Hotel Adlon Kempinski', 'Unter den Linden 77, 10117 Berlin', '+49 30 22610','4.5 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'hospedaje', 'hoteles', 'Mövenpick Hotel Berlin', 'Schöneberger Str. 3, 10963 Berlin', '+49 30 230060','4.2 de 5')");

         //berlin(hosteles)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'hospedaje', 'hosteles', 'Cityhostel Berlin', 'Glinkastraße 5-7, 10117 Berlin', '+49 30 238866850','3.5 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'hospedaje', 'hosteles', 'Sunflower Hostel', 'Helsingforser Str. 17, 10243 Berlin', '+49 30 44044250','4.2 de 5')");


         //berlin(lugares de acampar)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'hospedaje', 'lugar_acampar', 'Assateague State Park Camping', '7307 Stephen Decatur Highway, Berlin', '+1 410 641 2120','4.6 de 5')");

     }

     public void InsertarLugarInteres(SQLiteDatabase db){

        //madrid(lugares historicos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'lugar_interes', 'lugar_historico', 'Real Basilica de San Francisco el Grande', 'Calle de San Buenaventura, 1, 28005 Madrid', '34 913 65 38 00','4.5 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'lugar_interes', 'lugar_historico', 'Templo de Debod', 'Paseo de Rosales, Madrid', '34 913 66 74 15','4.5 de 5')");

        //madrid(museos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'lugar_interes', 'museos', 'Museo del Prado', 'Paseo del Prado, 28014 ', '34 913 30 28 00','4.6 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'lugar_interes', 'museos', 'Museo Centro de Arte Reina Sofia', 'Calle de Santa Isabel, 52, 28012 Madrid', '34 917 74 10 00','4.3 de 5')");

        //madrid(tour por la ciudad)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'lugar_interes', 'tour_ciudad', 'Madrid’s City Tour', 'Calle de Felipe IV, 28014 Madrid', '34 913 69 27 32','4.0 de 5')");

        //paris(lugares historicos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'lugar_historico', 'Torre Eiffel', 'Champ de Mars, 5 Avenue Anatole France, 75007 Paris, Francia', '33 892 70 12 39','4.7 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'lugar_historico', 'Catedral de Notre Dame', '6 Parvis Notre-Dame - Pl. Jean-Paul II, 75004 Paris, Francia', '33 1 42 34 56 10','4.6 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'lugar_historico', 'Arco de Triunfo de Paris', 'Place Charles de Gaulle, 75008 Paris, Francia', '33 1 55 37 73 77','4.5 de 5')");

        //paris(museos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'museos', 'Museo del Louvre', '75001 Paris, Francia', '33 1 40 20 50 50','4.6 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'museos', 'Muse d’Orsay', 'Rue de la Legion d Honneur, 75007 Paris', '33 1 40 49 48 14','4.5 de 5')");


        //paris(tour por la ciudad)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'tour_ciudad', 'L’ OpenTour', '13 Rue Auber, 75009 Paris', '33 1 42 66 56 56','2.8 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'lugar_interes', 'tour_ciudad', 'Foxity', '3 Rue de la Chaussee D’ Antin, 75009 Paris', '33 1 40 17 09 22','2.9 de 5')");


        //berlin(lugares historicos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'lugar_interes', 'lugar_historico', 'Parlamento Federal(Bundestag) ', 'Platz der Republik, Tiergarten, 11011 Berlin', '49 30 227 32152','4.7 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'lugar_interes', 'lugar_historico', 'Muro de Berlin', 'Bernauer Strasse 111/119, 13355 Berlin', '49 30 46798666','4.8 de 5')");


        //berlin(museos)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'lugar_interes', 'museos', 'Pergamonmuseum', 'Bodestraße 1-3, 10178 Berlin', '49 30 266424242','4.4 de 5')");

        db.execSQL("INSERT INTO " + TABLE_NAME4 + " (" + ID_ciudad3 + ", " + CN_categoria + ", " + CN_sub_cat + ",  " + CN_nombre + ", " + CN_dato1 + ", " + CN_dato2 + "," + CN_dato3 + ") " +
                "values('berlin', 'lugar_interes', 'museos', 'Neues Museum', 'Bodestrasse 1-3, 10178 Berlin', '49 30 266 424242','4.3 de 5')");


        //berlin(playas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('belrin', 'lugar_interes', 'playas', 'Strandbad Wannsee ', 'Wannseebadweg 25, 14129 Berlin', '49 30 787325','4.4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('belrin', 'lugar_interes', 'playas', 'Ku damm Beach', 'Koenigsallee 5B, 14193 Berlin', '49 30 8928597','3.5 de 5')");

        //berlin(tour por la ciudad)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'lugar_interes', 'tour_ciudad', 'New Berlin Tours', 'Pariser Platz 4A, 10117 Berlin', '49 30 51050030','3.9 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'lugar_interes', 'tour_ciudad', 'BCT Berlin City Tour', 'Bessemerstrasse 84, 12103 Berlin', '49 30 68302641','4.1 de 5')");


         //roma(lugares historicos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'lugar_historico', 'Coliseo', 'Piazza del Colosseo, 1, 00184 Roma, Italia', '39 06 3996 7700','4.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'lugar_historico', 'Panteon de Agripa', 'Piazza della Rotonda, 00186 Roma, Italia', '39 06 6830 0230','4.7 de 5')");

         //roma(museos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'museos', 'Museos Vaticanos', 'Viale Vaticano, 00165 Roma, Italia', '39 06 6988 3332','4.5 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'museos', 'Museo Nacional Etrusco', 'Piazzale di Villa Giulia, 9, 00197 Roma RM, Italia', '39 06 320 1706','4.4 de 5')");


        //roma(tour por la ciudad)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'tour_ciudad', 'New Rome Free Tour', 'Piazza di Spagna, 26, 00187 Roma', '39 06 6227 0995','4.2 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'lugar_interes', 'tour_ciudad', 'Rome Segway Tours', 'Via di Santa Eufemia 15, 00187 Roma', '39 055 239 8855','4.9 de 5')");


         //venecia(lugares historicos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'lugar_historico', 'San Giorgio Maggiore', 'Isola San Giorgio Maggiore, 30124 Venecia', '39 041 522 7827','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'lugar_historico', 'Campanile di San Marco', 'San Marco 328, 31024 Venecia', '39 041 522 5205','3.9 de 5')");

         //venecia(museos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'museos', 'Palacio Ducal', 'San Marco, 1, 30124 Venecia', '39 041 271 5911','4.7 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'museos', 'Gallerie dell Accademia', 'Campo della Carita, 30130 Venecia', '39 041 520 0345','4.4 de 5')");

         //venecia(playas)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'playas', 'Lido di Venezia', 'Lido di Venezia, 30100', '39 041 367 8268','4.2 de 5')");

        //venecia(tour por la ciudad)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'tour_ciudad', 'SeeVenice', 'Giudecca, 30133 Venecia', '39 349 084 8303','4.4 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'lugar_interes', 'tour_ciudad', 'Venice Photo Walk', 'San Marco, 30124 Venecia', '39 041 963 7374','5 de 5')");

         //londres(lugares historicos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'lugar_interes', 'lugar_historico', 'St. Paul’s Cathedral', 'St. Paul’s Churchyard, London EC4M 8AD', '44 20 7246 8350','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'lugar_interes', 'lugar_historico', 'Big Ben', 'Westminster, London SW1A 0AA', '44 20 7219 4272','4.7 de 5')");

         //londres(museos)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'lugar_interes', 'museos', 'The National gallery', 'Trafalgar Square, London WC2N 5DN', '44 20 7747 2885','4.5 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'lugar_interes', 'museos', 'The British Museum', 'Great Russell St, London WC1B 3DG', '44 20 7323 8299','4,6 de 5')");

         //londres(tour por la ciudad)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'lugar_interes', 'tour_ciudad', 'Tower of London', 'London EC3N 4AB', '44 844 482 7777','4.4 de 5')");





     }

     public void InsertarEntretenimiento(SQLiteDatabase db){

        //madrid(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'bares', 'Pajaritos Mojados', 'Calle del Humilladero, 3, Cebada, 28005 Madrid', '34 912 21 88 89','4.2 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'bares', 'Taberna Degusta', 'Francisco Silvela 83, 28028 Madrid', '34 910 25 71 53','4.3 de 5')");

        //madrid(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'discotecas', 'Teatro Kapital', 'Calle de Atocha, 125, 28012 Madrid', '34 914 20 29 06','3.8 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'discotecas', 'Joy Eslava', 'Calle del Arenal, 11, 28013 Madrid', '34 913 66 37 33','3.7 de 5')");

        //madrid(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'clubes', 'Macumba', 'Plaza Estación de Charmartin, Madrid 28036', '34 917 33 35 05','3.4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'clubes', 'Maxime', 'Ronda de toledo 1, linea 5 metro', '34 91 733 3505','3.1 de 5')");


        //madrid(parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'parque_diversion', 'Madrid Theme Park', 'Casa de campo, s/n, 28011 madrid', '34 902 34 50 01','4.1 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('madrid', 'entretenimiento', 'parque_diversion', 'Parque Warner', 'M-301, Km 15.5, 28330 San Martin de la Vega', '34 902 02 41 00','4.2 de 5')");

        //paris(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'bares', 'Brewberry bar et cave à bières', '11/18 Rue du Pot de Fer, 75005 Paris, Francia', '33 1 43 36 53 92','4.4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'bares', 'Sherry Butt', '20 Rue Beautreillis, 75004 Paris, Francia', '33 9 83 38 47 80','4.5 de 5')");

        //paris(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'discotecas', 'Le Balajo', '9 Rue de Lappe, 75011 Paris', '33 1 47 00 07 87','3.3 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'discotecas', 'Bus Palladium', '6 Rue Pierre Fontaine, 75009', '33 1 45 26 80 35','3.5 de 5')");

        //paris(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'clubes', 'Batofar', '11 quai Francois-Mauriac', '33 1 56 29 10 00','4.3 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'clubes', 'Le Truskel', '12 Rue Feydeau, 75002 Paris','33 1 40 26 59 97','3.4 de 5')");

        //paris(parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('paris', 'entretenimiento', 'parque_diversion', 'Disneyland Paris', 'Disneyland Paris, Marne-la-Vallée, Francia', '33 825 30 05 00','4.6 de 5')");


        //berlin(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'bares', 'Rivabar', 'Dircksenstraße 142, 10178 ', '49 30 24722688','4.2 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'bares', 'Fragances', 'Potsdamer Platz 3, 76227 Berlin', '49 30 337777','4.0 de 5')");


        //belin(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'discotecas', 'Berghain', 'Am Wriezener Bahnhof, 10243 Berlin', '49 30 29360210','4.1 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'discotecas', 'Tresor Club', 'Köpenicker Str. 70, 10179 Berlin', '49 30 69537731','4.0 de 6')");

        //berlin(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'clubes', 'Club Maxxim', 'Joachimsthaler Str. 15, 10719 Berlin', '49 30 41766240','2.9 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'clubes', 'Club der Visionäre', 'Am Flutgraben 1, 12435 Berlin', '49 30 69518942','4.2 de 5')");

        //berlin (parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'parque_diversion', 'Jacks Fun World', 'Miraustraße 38, 13509 Berlin', '49 30 41900242','2.7 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('berlin', 'entretenimiento', 'parque_diversion', 'LEGOLAND Discovery Centre', 'Postdamer Straße 4, 10785 Berlin', '49 180 6 666901','2.9 de 5')");


        //roma(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'bares', 'Wine and Food Tasting Roscioli', 'Via dei Giubbonari, 21, 00186 Roma, Italia', '39 333 778 0024','5 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'bares', 'Cul de Sac Wine Bar', 'Piazza Pasquino 73, 00186 Roma, Italia', ' 39-06-6880-1094','4.5 de 5')");

        //roma(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'discotecas', 'Level', 'Vicolo del Fico, 3, 00186 Roma', '39 334 850 1706','4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'discotecas', 'Circolo degli Artisti', 'Via Casilina Vecchia, 42, 00182 Roma', '39 06 7030 5684','4 de 5')");

        //roma(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'clubes', 'Colors Club', 'Via della Scala 43, Roma', '39 333 213 9272','4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'clubes', 'BeBop Jazz Club', 'Via Giuseppe Giulietti, 14, 00154 Roma', '39 06 575 5582','4.3 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'clubes', 'Ice Club', 'Via Madonna dei monti, 18/19, 00184 Roma, Italia', '39 069 784 5581','5 de 5')");


        //roma(parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'parque_diversion', 'Escape Rome', 'Via Carlo Botta 11, 00184 Roma, Italia', '39 32 4632 0974','5 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('roma', 'entretenimiento', 'parque_diversion', 'Excape', 'Via di Monserrato, 104, 00186 Roma, Italia', '39 06 6476 0841','5 de 5')");


        //venecia(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'bares', 'All Arco', 'Sestiere San Polo, 436, 30125 Venecia', '39 041 520 566','4.8 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'bares', 'Osteria Antico Dolo', 'Ruga Rialto, 778, 30175 Venecia', '39 041 522 6546','3.9 de 5')");

        //venecia(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'discotecas', 'Piccolo Mondo Music Dance', 'Dorsoduro, 1056/a, 30100 Venecia', '39 041 520 0371','3.8 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'discotecas', 'Sound Garden', 'Via Aleardi 18A, Venecia', '39 0421 971 902','3.5 de 5')");

        //venecia(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'clubes', 'Bacaro Jazz', 'S. Marco, 5546, 30124 Venecia', '39 041 528 5249','3.3 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'clubes', 'Paradiso Perdutto', 'Fondamenta della Misericordia, 2540, 30121', '39 041 720581','4.3 de 5')");

        //venecia(parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('venecia', 'entretenimiento', 'parque_diversion', 'Shark Bay', 'Via Michelangelo Buonarroti, 30016 Venecia', '39 042 137 1648','3.8 de 5')");



        //londres(bares)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'bares', 'Artesian', 'The Langham, London W1B 1JA', '44 20 7636 1000','4.4 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'bares', 'Gordon’s Wine Bar', '47 Villiers St, London WC2N 6NE', '44 20 7930 11408','4.4 de 5')");

        //londres(discotecas)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'discotecas', 'The Old School Yard', '109-111 Long Ln, London SE1 4PH', '44 20 7357 6281','4.3 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'discotecas', 'Eagle London', '349 Kennington Ln, London SE11 5QY', '44 20 7793 0903','4.3 de 5')");

        //londres(clubes)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'clubes', 'City of London Club', '19 Old Broad St, Greater London EC2N 1DS', '44 20 7588 7991','3.9 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'clubes', 'Carlton Club', '68 St James s St, London SW1A 1PJ', '44 20 7493 1164','3.8 de 5')");

        //londres(parque de diversiones)
        //dato 1: direccion dato2: telefono dato3: rating

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'parque_diversion', 'Chessington World of Adventures', 'Leatherhead Rd, Chessinton, Surrey KT9  2NE', '44 871 663 4477','4.1 de 5')");

        db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+", "+CN_sub_cat+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                "values('londres', 'entretenimiento', 'parque_diversion', 'Winter Wonderland', 'Hyde Park, London W2 2ET', '44 20 8241 9818','3.9 de 5')");



    }

     public void  InsertarCompras(SQLiteDatabase db){

        //madrid(compras)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'compras','Mercado San Miguel', 'Plaza de San Miguel, 28005 Madrid', '34 915 42 49 36','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('madrid', 'compras','Sherry Corner', 'Plaza de San Miguel, 28013 Madrid', '34 681 00 77 00','4.1 de 5')");

         //paris(compras)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'compras','La Galerie du Carrousel du Louvre', '99 Rue de Rivoli, 75001 Paris, Francia', '33 1 43 16 47 10','4.2 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'compras','Passy Plaza', '3 Rue Jean Bologne, 75016 Paris, Francia', '33 1 40 50 09 07','4.2 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'compras','Hermès', '42 Avenue George V, 75008 Paris, Francia', '33 1 47 20 48 51','4.3 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('paris', 'compras','Chanel Cambon', '31 Rue Cambon, 75001 Paris, Francia', '33 1 44 50 66 00','4.6 de 5')");

         //berlin(compras)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'compras','ALEXA', 'Grunestr, 20 Berlin', '49 30 2693400','4.0 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('berlin', 'compras','Schönhauser Allee Arcaden', 'Alte Postdamer Str. 7 Berlin', '49 30 2559270','3.1 de 5')");

         //roma(compras)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'compras','Stone Island Roma', 'Via del Babuino, 174, 00187 Roma', '39 06 3600 0836','4.8 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('roma', 'compras','Subdued', 'Via Laurina, 37, Roma', '39 06 8916 5427','4.5 de 5')");

         //venecia(compras)
         //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'compras','Ca’ MAcana', 'Calle delle Botteghe 30123 Venecia', '39 41 277 6142','4.6 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('venecia', 'compras','Raggio Veneziano', 'San Marco 2953 Venecia', '39 041 241 2712','4.4 de 5')");


        //londres(compras)
        //dato 1: direccion dato2: telefono dato3: rating

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'compras','Westfield London', '4006, Ariel Way, London W12 7GF', '44 20 3371 2300','4.4 de 5')");

         db.execSQL("INSERT INTO "+TABLE_NAME4+" ("+ID_ciudad3+", "+CN_categoria+",  "+CN_nombre+", "+CN_dato1+", "+CN_dato2+","+CN_dato3+") " +
                 "values('londres', 'compras','Whiteleys', 'Queensway, London W2 4YN', '44 20 7229 8844','3.7 de 5')");



     }
}
