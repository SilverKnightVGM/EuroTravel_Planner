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
    private static final int DB_SCHEMA_VESION= 6;
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

    public static String where = null;
    public static String where2 = null;


    // Query que creara tabla Datos Generales
    private static final String TABLE_CREATE="CREATE TABLE "+TABLE_NAME+" ("+ID_datos+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+ID_ciudad+" text not null, "+CN_info+" text not null, "+CN_segundo_nombre+" text ,"+CN_lema+" text ,"+
            " "+CN_clima+" text not null, "+CN_moneda+" text not null, "+CN_demografia+" text not null)";

    private static final String TABLE_CREATE2="CREATE TABLE "+TABLE_NAME2+" ("+ID_tip+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+CN_tip+" text not null, "+ID_ciudad2+" text not null, "+CN_tipo+" text not null)";

    private static final String TABLE_CREATE3="CREATE TABLE "+TABLE_NAME3+" ("+CN_trans+" Integer, "+
            " "+CN_hosp+" Integer, "+CN_gas+" Integer, "+CN_entre+" Integer,"+CN_lugar+" Integer ,"+
            " "+CN_comp+" Integer)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE2);
        db.execSQL(TABLE_CREATE3);

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

        InsertarTips(db);

        //db.execSQL("INSERT INTO "+TABLE_NAME2+" ("+CN_tip+","+ID_ciudad2+" ,"+CN_tipo+") " +
        //        "values('Si viajas desde otro continente, muchas aerolíneas ofrecen tiquetes más baratos hacia ciudades diferentes a París. Puede ser útil arribar primero a otra ciudad y luego llegar a la capital francesa en tren.', 'paris', 'transporte')");


        this.db = db;

    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VESION);

    }

    public Cursor getDatos(){
        String columnas[]={ID_datos,ID_ciudad,CN_info,CN_segundo_nombre,CN_lema,CN_clima,CN_moneda,CN_demografia};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME,columnas,where,null,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getTips(){
        String columnas[]={CN_tip};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME2,columnas,where2,null,null,null,"RANDOM()");
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


}
