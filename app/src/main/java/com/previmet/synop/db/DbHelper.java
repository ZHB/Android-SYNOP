package com.previmet.synop.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    private String[] countries = {"Suisse", "France", "Belgique"};
    private String[] stations = {"Suisse", "France", "Belgique"};


    public DbHelper(Context context) {

        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbContract.Country.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Station.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_CREATE_TABLE);

        // populate some initial values
        Db.initialize(db);

        // countries
        long idSuisse = Db.addCountry("Suisse");
        long idFrance = Db.addCountry("France");
        long idBelgique = Db.addCountry("Belgique");

        Db.addStation("Koksijde", "06400", 4, 51.0848,2.6468, idBelgique);
        Db.addStation("Oostende", "06407",5,51.2002,2.8701,idBelgique);
        Db.addStation("Roeselare/Beitem", "06414",25,50.8999,3.1195,idBelgique);
        Db.addStation("Charleroi", "06449",187,50.4666,4.4508,idBelgique);
        Db.addStation("Bruxelles-National", "06451",55,50.901,4.5339,idBelgique);
        Db.addStation("Florennes", "06456",279,50.232,4.648,idBelgique);
        Db.addStation("St-Hubert", "06476",557,50.0282,5.4175,idBelgique);
        Db.addStation("Kleine Brogel", "06479",55,51.1681,5.4658,idBelgique);
        Db.addStation("Buzenol", "06484",324,49.6202,5.5797,idBelgique);
        Db.addStation("Elsenborn", "06496",564,50.4666,6.1844,idBelgique);
        Db.addStation("Basel / Binningen", "06601",316,47.5459,7.5498,idSuisse);
        Db.addStation("Delémont", "06602",439,47.351702,7.349554,idSuisse);
        Db.addStation("Neuchâtel", "06604",485,46.9999,6.9531,idSuisse);
        Db.addStation("Chasseral", "06605",1594,47.1317,7.0543,idSuisse);
        Db.addStation("Cressier", "06606",431,47.0476,7.0591,idSuisse);
        Db.addStation("Chaumont", "06608",1136,47.04916,6.978821,idSuisse);
        Db.addStation("Le Moléson", "06609",1974,46.5461,7.0178,idSuisse);
        Db.addStation("Payerne", "06610",490,46.8115,6.9424,idSuisse);
        Db.addStation("La Chaux-de-Fonds", "06612",1017,47.0832,6.7923,idSuisse);
        Db.addStation("Fahy", "06616",596,47.4238,6.9411,idSuisse);
        Db.addStation("La Brévine", "06617",1050,46.983836,6.61029,idSuisse);
        Db.addStation("Mathod", "06618",437,46.737018,6.56803,idSuisse);
        Db.addStation("Bullet / La Frétaz", "06619",1205,46.8406,6.5763,idSuisse);
        Db.addStation("Schaffhausen", "06620",438,47.6897,8.62,idSuisse);
        Db.addStation("Güttingen", "06621",440,47.6017,9.2793,idSuisse);
        Db.addStation("Salen Reutenen", "06623",718,47.651199,9.023931,idSuisse);
        Db.addStation("Hallau", "06624",419,47.6972,8.4704,idSuisse);
        Db.addStation("Fribourg / Posieux", "06625",646,46.7713,7.1137,idSuisse);
        Db.addStation("Gösgen", "06626",380,47.363,7.9736,idSuisse);
        Db.addStation("Château-d'Oex", "06627",1029,46.4797,7.1396,idSuisse);
        Db.addStation("Plaffeien", "06628",1042,46.7476,7.2659,idSuisse);
        Db.addStation("Bern / Zollikofen", "06631",553,46.9466,7.4922,idSuisse);
        Db.addStation("Grenchen", "06632",430,47.179,7.4151,idSuisse);
        Db.addStation("Buchs / Aarau", "06633",387,47.3843,8.0794,idSuisse);
        Db.addStation("Koppigen", "06635",484,47.118843,7.605486,idSuisse);
        Db.addStation("Mühleberg", "06636",480,46.9732,7.2781,idSuisse);
        Db.addStation("Meiringen", "06637",588,46.7321,8.1692,idSuisse);
        Db.addStation("Langnau", "06638",745,46.9396,7.8064,idSuisse);
        Db.addStation("Napf", "06639",1404,47.0046,7.94,idSuisse);
        Db.addStation("Möhlin", "06641",344,47.5721,7.8778,idSuisse);
        Db.addStation("Wynau", "06643",422,47.255,7.7874,idSuisse);
        Db.addStation("Mosen", "06644",452,47.243801,8.232782,idSuisse);
        Db.addStation("Rünenberg", "06645",611,47.4345,7.8793,idSuisse);
        Db.addStation("Beznau", "06646",326,47.5572,8.2332,idSuisse);
        Db.addStation("Egolzwil", "06648",521,47.1793,8.0047,idSuisse);
        Db.addStation("Luzern", "06650",454,47.0364,8.3009,idSuisse);
        Db.addStation("Schüpfheim", "06651",742,46.947,8.0123,idSuisse);
        Db.addStation("Engelberg", "06655",1036,46.8216,8.4104,idSuisse);
        Db.addStation("Brienz", "06656",567,46.740757,8.060776,idSuisse);
        Db.addStation("Giswil", "06657",475,46.8494,8.1901,idSuisse);
        Db.addStation("Pilatus", "06659",2106,46.9788,8.2523,idSuisse);
        Db.addStation("Zürich / Fluntern", "06660",556,47.3778,8.5657,idSuisse);
        Db.addStation("Zürich / Affoltern", "06664",444,47.427676,8.517885,idSuisse);
        Db.addStation("Leibstadt", "06666",341,47.5972,8.1882,idSuisse);
        Db.addStation("Laegern", "06669",868,47.48,8.4,idSuisse);
        Db.addStation("Zürich-Kloten", "06670",436,47.4796,8.536,idSuisse);
        Db.addStation("Steckborn", "06671",398,47.6686,8.9814,idSuisse);
        Db.addStation("Altdorf", "06672",438,46.887,8.6218,idSuisse);
        Db.addStation("Wädenswil", "06673",485,47.2206,8.6777,idSuisse);
        Db.addStation("Cham", "06674",442,47.18829,8.46462,idSuisse);
        Db.addStation("Einsiedeln", "06675",910,47.132956,8.756531,idSuisse);
        Db.addStation("Oberägeri", "06676",724,47.13358,8.60808,idSuisse);
        Db.addStation("Aadorf / Tänikon", "06679",539,47.4798,8.9048,idSuisse);
        Db.addStation("Säntis", "06680",2502,47.2494,9.3436,idSuisse);
        Db.addStation("St. Gallen", "06681",776,47.4254,9.3984,idSuisse);
        Db.addStation("Elm", "06682",958,46.9237,9.1753,idSuisse);
        Db.addStation("Schmerikon", "06683",408,47.2246,8.9402,idSuisse);
        Db.addStation("Glarus", "06685",517,47.0345,9.0669,idSuisse);
        Db.addStation("Bad Ragaz", "06686",496,47.0165,9.5025,idSuisse);
        Db.addStation("Quinten", "06687",419,47.1287,9.216,idSuisse);
        Db.addStation("Crap Masegn", "06688",2480,46.8422,9.1799,idSuisse);
        Db.addStation("Hörnli", "06689",1132,47.37085,8.941612,idSuisse);
        Db.addStation("Altenrhein", "06690",398,47.4836,9.5667,idSuisse);
        Db.addStation("Ebnat-Kappel", "06693",623,47.2733,9.1085,idSuisse);
        Db.addStation("Andermatt", "06695",1438,46.630825,8.58051,idSuisse);
        Db.addStation("Genève-Cointrin", "06700",413,46.2474,6.1277,idSuisse);
        Db.addStation("La Dôle", "06702",1670,46.4246,6.0994,idSuisse);
        Db.addStation("Bière", "06704",684,46.5248,6.3424,idSuisse);
        Db.addStation("Nyon / Changins", "06705",409,46.401,6.2277,idSuisse);
        Db.addStation("St-Prex", "06706",425,46.4836,6.443,idSuisse);
        Db.addStation("Villars - Tiercelin", "06707",859,46.62176,6.71007,idSuisse);
        Db.addStation("Oron", "06708",827,46.5722,6.8581,idSuisse);
        long idBouveret = Db.addStation("Bouveret", "06709",374,46.393444,6.857012,idSuisse);
        Db.addStation("Pully", "06711",456,46.5123,6.6674,idSuisse);
        long idAigle = Db.addStation("Aigle", "06712",408,46.3266,6.9244,idSuisse);
        Db.addStation("Les Diablerets", "06714",2964,46.3267,7.2037,idSuisse);
        Db.addStation("Evionnaz", "06715",482,46.18295,7.02675,idSuisse);
        Db.addStation("Col du Grand St-Bernard", "06717",2472,45.8687,7.1707,idSuisse);
        Db.addStation("Sion", "06720",482,46.2291,7.3619,idSuisse);
        Db.addStation("Binn", "06721",1479,46.36766,8.1923,idSuisse);
        Db.addStation("Evolene-Villaz", "06722",1825,46.1121,7.5086,idSuisse);
        Db.addStation("Les Attelas", "06723",2727,46.099122,7.268748,idSuisse);
        Db.addStation("Montana", "06724",1427,46.2987,7.4607,idSuisse);
        Db.addStation("Blatten, Lötschental", "06725",1538,46.420398,7.823196,idSuisse);
        Db.addStation("Visp", "06727",639,46.2967,7.8846,idSuisse);
        Db.addStation("Grächen", "06728",1605,46.195314,7.83679,idSuisse);
        Db.addStation("Jungfraujoch", "06730",3580,46.5474,7.9853,idSuisse);
        Db.addStation("Thun", "06731",570,46.74976,7.58525,idSuisse);
        Db.addStation("Boltigen", "06733",820,46.623536,7.384165,idSuisse);
        Db.addStation("Interlaken", "06734",577,46.6722,7.8701,idSuisse);
        Db.addStation("Adelboden", "06735",1320,46.4947,7.5704,idSuisse);
        Db.addStation("Eggishorn", "06739",2893,46.4265,8.0927,idSuisse);
        Db.addStation("Titlis", "06740",3040,46.770507,8.425805,idSuisse);
        Db.addStation("Grimsel Hospiz", "06744",1980,46.5716,8.3332,idSuisse);
        Db.addStation("Ulrichen", "06745",1346,46.5048,8.3081,idSuisse);
        Db.addStation("Monte Rosa-Plattje", "06747",2885,45.9566,7.8145,idSuisse);
        Db.addStation("Zermatt", "06748",1638,46.0218,7.7484,idSuisse);
        Db.addStation("Gornergrat", "06749",3129,46.0114,7.7668499,idSuisse);
        Db.addStation("Gütsch ob Andermatt", "06750",2287,46.653457,8.616244,idSuisse);
        Db.addStation("Robièi", "06751",1895,46.443,8.5133,idSuisse);
        Db.addStation("Cevio", "06752",417,46.32049,8.603162,idSuisse);
        Db.addStation("Piotta", "06753",990,46.5147,8.6881,idSuisse);
        Db.addStation("Matro", "06754",2171,46.4101,8.92473,idSuisse);
        Db.addStation("Acquarossa / Comprovasco", "06756",575,46.4594,8.9356,idSuisse);
        Db.addStation("Grono", "06758",324,46.254991,9.163721,idSuisse);
        Db.addStation("Cimetta", "06759",1661,46.20042,8.791642,idSuisse);
        Db.addStation("Locarno / Monti", "06760",383,46.1722,8.7874,idSuisse);
        Db.addStation("Magadino / Cadenazzo", "06762",203,46.16,8.9336,idSuisse);
        Db.addStation("Lugano", "06770",300,46.0038,8.9601,idSuisse);
        Db.addStation("Stabio", "06771",353,45.8433,8.9321,idSuisse);
        Db.addStation("Monte Generoso", "06777",1600,45.927605,9.017872,idSuisse);
        Db.addStation("Buffalora", "06778",1968,46.6481,10.2671,idSuisse);
        Db.addStation("Segl - Maria", "06779",1804,46.43232,9.76231,idSuisse);
        Db.addStation("Weissfluhjoch", "06780",2690,46.8333,9.8063,idSuisse);
        Db.addStation("Disentis", "06782",1197,46.7065,8.8534,idSuisse);
        Db.addStation("S. Bernardino", "06783",1639,46.4635,9.1846,idSuisse);
        Db.addStation("Davos", "06784",1594,46.8129,9.8434,idSuisse);
        Db.addStation("Chur", "06786",556,46.8704,9.5305,idSuisse);
        Db.addStation("Andeer", "06787",987,46.6101,9.4319,idSuisse);
        Db.addStation("Vicosoprano", "06788",1089,46.35297,9.627688,idSuisse);
        Db.addStation("Ilanz", "06789",698,46.77504,9.21533,idSuisse);
        Db.addStation("Schiers", "06790",626,46.9755,9.66805,idSuisse);
        Db.addStation("Piz Corvatsch", "06791",3305,46.418,9.8211,idSuisse);
        Db.addStation("Samedan", "06792",1709,46.5264,9.8781,idSuisse);
        Db.addStation("Valbella", "06793",1569,46.7549,9.5544,idSuisse);
        Db.addStation("Poschiavo / Robbia", "06794",1078,46.3466,10.0611,idSuisse);
        Db.addStation("Piz Martegnas", "06795",2670,46.5773,9.5296,idSuisse);
        Db.addStation("Sta. Maria, Val Müstair", "06796",1383,46.602168,10.426265,idSuisse);
        Db.addStation("Scuol", "06798",1304,46.7932,10.2832,idSuisse);
        Db.addStation("Naluns / Schlivera", "06799",2400,46.8171,10.2613,idSuisse);
        Db.addStation("Boulogne", "07002",73,50.7339,1.5978,idFrance);
        Db.addStation("Le Touquet-Paris-Plage", "07003",10,50.5163,1.6241,idFrance);
        Db.addStation("Abbeville", "07005",70,50.1331,1.8378,idFrance);
        Db.addStation("Dunkerque", "07010",11,51.0539,2.3412,idFrance);
        Db.addStation("Lille-Lesquin", "07015",48,50.5669,3.099,idFrance);
        Db.addStation("Cap de La Hague", "07020",3,49.7263,-1.9397,idFrance);
        Db.addStation("Cherbourg - Maupertus", "07024",135,49.6484,-1.4739,idFrance);
        Db.addStation("Caen-Carpiquet ", "07027",78,49.1809,-0.4606,idFrance);
        Db.addStation("Le Havre - Cap de la Hève", "07028",100,49.5093,0.0684,idFrance);
        Db.addStation("Deauville", "07031",146,49.3667,0.0832,idFrance);
        Db.addStation("Rouen-Boos", "07037",151,49.3864,1.1835,idFrance);
        Db.addStation("Evreux - Fauville", "07038",138,49.0261,1.2218,idFrance);
        Db.addStation("Dieppe", "07040",33,49.9343,1.0948,idFrance);
        Db.addStation("Beauvais-Tille", "07055",89,49.4468,2.1285,idFrance);
        Db.addStation("Creil", "07057",88,49.2603,2.5173,idFrance);
        Db.addStation("Saint-Quentin - Roupy", "07061",98,49.8188,3.2066,idFrance);
        Db.addStation("Reims-Prunay", "07072",83,49.2,4.1581,idFrance);
        Db.addStation("Charleville-Mezieres", "07075",149,49.7828,4.6345,idFrance);
        Db.addStation("Metz / Frescaty", "07090",190,49.0725,6.1231,idFrance);
        Db.addStation("Ouessant-Stiff", "07100",35,48.4762,-5.0532,idFrance);
        Db.addStation("Brest", "07110",99,48.3982,-4.4911,idFrance);
        Db.addStation("Ploumana'ch - Perros", "07117",50,48.827,-3.4727,idFrance);
        Db.addStation("Lannion - Servel", "07118",87,48.757,-3.468,idFrance);
        Db.addStation("Saint-Brieuc - Armor", "07120",136,48.537,-2.8513,idFrance);
        Db.addStation("Dinard - St Malo", "07125",58,48.5902,-2.0732,idFrance);
        Db.addStation("Rennes-St Jacques", "07130",37,48.0711,-1.7369,idFrance);
        Db.addStation("Laval-Entrammes", "07134",96,48.0335,-0.7362,idFrance);
        Db.addStation("Alençon - Valframbert", "07139",144,48.4443,0.1067,idFrance);
        Db.addStation("Chartres - Champhol", "07143",155,48.4617,1.512,idFrance);
        Db.addStation("Trappes", "07145",168,48.7706,2.0083,idFrance);
        Db.addStation("Toussus Le Noble", "07146",154,48.7506,2.1073,idFrance);
        Db.addStation("Villacoublay - Vélizy", "07147",174,48.7708,2.2045,idFrance);
        Db.addStation("Paris-Orly", "07149",89,48.7219,2.3524,idFrance);
        Db.addStation("Paris / Le Bourget", "07150",93,48.967,2.425,idFrance);
        Db.addStation("Melun - Villaroche", "07153",91,48.6103,2.8,idFrance);
        Db.addStation("Paris-Montsouris", "07156",75,48.8217,2.3374,idFrance);
        Db.addStation("Paris - Roissy/Charles-de-Gaulle", "07157",108,49.0191,2.5338,idFrance);
        Db.addStation("Troyes-Barberey", "07168",112,48.328,4.0229,idFrance);
        Db.addStation("Saint-Dizier - Robinson", "07169",139,48.6355,4.9028,idFrance);
        Db.addStation("Nancy-Essey", "07180",212,48.6872,6.2218,idFrance);
        Db.addStation("Nancy-Ochey", "07181",336,48.579,5.9571,idFrance);
        Db.addStation("Strasbourg", "07190",153,48.5391,7.6222,idFrance);
        Db.addStation("Quimper-Pluguffan", "07201",92,47.9732,-4.1724,idFrance);
        Db.addStation("Ile de Groix - Beg Melen", "07203",45,47.6529,-3.5023,idFrance);
        Db.addStation("Lorient-Lann Bihoué", "07205",42,47.768,-3.4418,idFrance);
        Db.addStation("Nantes-Atlantique", "07222",27,47.1561,-1.6055,idFrance);
        Db.addStation("Angers - Marcé", "07230",59,47.5793,-0.3274,idFrance);
        Db.addStation("Tours - St Symphorien", "07240",112,47.4449,0.7287,idFrance);
        Db.addStation("Blois", "07245",121,47.6784,1.2097,idFrance);
        Db.addStation("Romorantin - Pruniers", "07247",84,47.3166,1.6825,idFrance);
        Db.addStation("Orléans - Bricy", "07249",126,47.9912,1.7497,idFrance);
        Db.addStation("Bourges", "07255",161,47.0582,2.3617,idFrance);
        Db.addStation("Nevers-Marzy", "07260",180,46.9994,3.1133,idFrance);
        Db.addStation("Auxerre", "07265",207,47.8049,3.5448,idFrance);
        Db.addStation("Dijon", "07280",222,47.3166,5.0166,idFrance);
        Db.addStation("Langres", "07283",467,47.8444,5.3365,idFrance);
        Db.addStation("Besançon", "07288",307,47.25,6.0333,idFrance);
        Db.addStation("Luxeuil", "07292",278,47.7818,6.3867,idFrance);
        Db.addStation("Bâle-Mulhouse", "07299",263,47.6151,7.5092,idFrance);
        Db.addStation("Ile d'Yeu - St Sauveur", "07300",32,46.6939,-2.3303,idFrance);
        Db.addStation("La Roche sur Yon - Les Ajoncs", "07306",90,46.6998,-1.3821,idFrance);
        Db.addStation("Pointe de Chassiron", "07314",11,46.0477,-1.4105,idFrance);
        Db.addStation("La Rochelle-Le Bout Blanc", "07315",4,46.1523,-1.1594,idFrance);
        Db.addStation("Niort - Souche", "07330",59,46.3166,-0.4004,idFrance);
        Db.addStation("Poitiers-Biard", "07335",129,46.5812,0.3004,idFrance);
        Db.addStation("Chateauroux", "07354",160,46.8593,1.7261,idFrance);
        Db.addStation("Vichy-Charmeil", "07374",249,46.169,3.4013,idFrance);
        Db.addStation("Mâcon - Charnay", "07385",216,46.2969,4.799,idFrance);
        Db.addStation("Cognac-Châteaubernard", "07412",30,45.668,-0.313,idFrance);
        Db.addStation("Limoges-Bellegarde", "07434",396,45.8649,1.1839,idFrance);
        Db.addStation("Brive - La Roche", "07438",111,45.1484,1.4745,idFrance);
        Db.addStation("Clermont-Ferrand - Aulnat", "07460",332,45.7888,3.1631,idFrance);
        Db.addStation("Le Puy-Loudes", "07471",833,45.0754,3.7641,idFrance);
        Db.addStation("Lyon / Bron", "07480",200,45.75,4.85,idFrance);
        Db.addStation("Lyon / St-Exupéry", "07481",248,45.7215,5.0861,idFrance);
        Db.addStation("Amberieu", "07482",250,45.9612,5.3193,idFrance);
        Db.addStation("Grenoble / St. Geoirs", "07486",384,45.3614,5.3374,idFrance);
        Db.addStation("Chambery / Aix-Les-Bains", "07491",235,45.7,5.9166,idFrance);
        Db.addStation("Bourg-St-Maurice", "07497",865,45.613,6.7627,idFrance);
        Db.addStation("Lège - Cap Ferret", "07500",9,44.6319,-1.2478,idFrance);
        Db.addStation("Biscarrosse", "07503",33,44.4339,-1.2463,idFrance);
        Db.addStation("Bordeaux / Merignac", "07510",49,44.8332,-0.7193,idFrance);
        Db.addStation("Agen - La Garenne", "07524",59,44.177,0.5952,idFrance);
        Db.addStation("Bergerac-Roumaniere", "07530",51,44.8221,0.517,idFrance);
        Db.addStation("Gourdon", "07535",259,44.7469,1.3971,idFrance);
        Db.addStation("Aurillac", "07549",640,44.8955,2.422,idFrance);
        Db.addStation("Millau", "07558",715,44.1205,3.0199,idFrance);
        Db.addStation("Mont Aigoual", "07560",1567,44.1212,3.5814,idFrance);
        Db.addStation("Montélimar - Ancone", "07577",73,44.581,4.7377,idFrance);
        Db.addStation("Orange-Caritat", "07579",53,44.1405,4.8514,idFrance);
        Db.addStation("Carpentras", "07586",99,44.0832,5.0594,idFrance);
        Db.addStation("Château Arnoux - Saint-Auban", "07588",461,44.0634,5.9915,idFrance);
        Db.addStation("Saint-Jean-de-Luz - Pointe de Socoa", "07600",24,43.3949,-1.685,idFrance);
        Db.addStation("Biarritz-Anglet", "07602",69,43.472,-1.5319,idFrance);
        Db.addStation("Dax-Seyresse", "07603",31,43.6921,-1.0658,idFrance);
        Db.addStation("Mont-de-Marsan", "07607",59,43.9123,-0.4979,idFrance);
        Db.addStation("Pau-Uzein", "07610",188,43.3859,-0.4263,idFrance);
        Db.addStation("Tarbes - Ossun - Lourdes", "07621",360,43.1869,0.0051,idFrance);
        Db.addStation("Auch-Lamothe", "07622",121,43.6899,0.6031,idFrance);
        Db.addStation("Saint-Girons - Antichan", "07627",411,43.0021,1.1071,idFrance);
        Db.addStation("Toulouse / Blagnac", "07630",152,43.6397,1.3557,idFrance);
        Db.addStation("Albi-Le Séquestre", "07632",172,43.9152,2.1169,idFrance);
        Db.addStation("Carcassonne-Salvaza", "07635",126,43.2145,2.3099,idFrance);
        Db.addStation("Sète", "07641",80,43.3973,3.6926,idFrance);
        Db.addStation("Montpellier - Fréjorgues", "07643",5,43.5809,3.962,idFrance);
        Db.addStation("Marseille / Marignane", "07650",6,43.4381,5.2161,idFrance);
        Db.addStation("La Ciotat - Bec de l'Aigle", "07656",316,43.1749,5.5747,idFrance);
        Db.addStation("Toulon - La Mitre", "07660",24,43.1042,5.9328,idFrance);
        Db.addStation("Saint-Mandrier-sur-Mer - Cap Cépet", "07661",126,43.0781,5.9422,idFrance);
        Db.addStation("Leucate", "07666",42,42.9178,3.0599,idFrance);
        Db.addStation("Hyères - Le Palyvestre", "07667",2,43.0952,6.1473,idFrance);
        Db.addStation("Ile de Porquerolles", "7670",143,43.0,6.2276,idFrance);
        Db.addStation("Le Luc - Le Cannet-des-Maures", "07675",80,43.3843,6.3852,idFrance);
        Db.addStation("Ile du Levant", "07678",130,43.023,6.46,idFrance);
        Db.addStation("Cannes", "07684",3,43.5413,6.9511,idFrance);
        Db.addStation("Nice", "07690",4,43.7048,7.268,idFrance);
        Db.addStation("Perpignan - Rivesaltes", "07747",43,42.7437,2.8703,idFrance);
        Db.addStation("Cap Béar - Port-Vendres", "07749",82,42.5162,3.1335,idFrance);
        Db.addStation("Ajaccio - La Parata", "07752",140,41.9084,8.6186,idFrance);
        Db.addStation("Ile Rousse", "07753",142,42.6331,8.9225,idFrance);
        Db.addStation("Calvi - Ste Catherine", "07754",57,42.5245,8.7919,idFrance);
        Db.addStation("Ajaccio - Campo dell'Oro", "07761",6,41.9222,8.7955,idFrance);
        Db.addStation("Solenzara", "07765",17,41.9234,9.3957,idFrance);
        Db.addStation("Bonifacio - Cap Pertusato", "07770",105,41.3908,9.1661,idFrance);
        Db.addStation("Figari", "07780",22,41.508,9.0999,idFrance);
        Db.addStation("Ersa - Cap Corse", "07785",108,43.0056,9.3582,idFrance);
        Db.addStation("Bastia - Poretta", "07790",8,42.5509,9.4812,idFrance);
        Db.addStation("Sisco - Cap Sagro", "07791",111,42.798,9.4899,idFrance);


        // add default favorites stations
        Db.addFavorite(idAigle);
        Db.addFavorite(idBouveret);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // delete database
        db.execSQL(DbContract.Country.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Station.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_DELETE_TABLE);

        // recreate database
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}