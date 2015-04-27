package com.sam58.drv.repository;

import com.sam58.drv.model.DriverPerson;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by sam158 on 22.04.2015.
 */

@Repository
public class DriverPersonRepositoryImpl implements DriverPersonRepository {
   // private static final Logger log = Logger.getLogger(DriverPersonRepositoryImpl.class);
   private static final Logger log = Logger.getLogger("warp");


    //private static final Map<UUID, DriverPerson> UUID_DRIVER_MAP =  new TreeMap<>();

    private static final Map<String, DriverPerson> UUID_DRIVER_MAP = Collections.synchronizedMap(new HashMap<String, DriverPerson>());
    private static final Set<String> ONE_GRID_MAP=Collections.synchronizedSet(new HashSet<String>());

    static {
        List<String> fname=new ArrayList<String>(Arrays.asList(new String[]{"Иван", "Петр", "Сидор", "Терентий", "Сергей", "Вдадимир"}));
        List<String> lname=new ArrayList<String>(Arrays.asList(new String[]{"Иванов", "Петров", "Сидоров", "Турчик","Лесной","Куприянов"}));
        List<String> sname=new ArrayList<String>(Arrays.asList(new String[]{"Иванович", "Петрович", "Сидорович", "Сергеевич","Матвеевич","Евгеньевич"}));
        // List<ClassDriveLicense> drvLic =new ArrayList<ClassDriveLicense>(Arrays.asList(ClassDriveLicense.C,ClassDriveLicense.A,ClassDriveLicense.E,ClassDriveLicense.B,ClassDriveLicense.D));

        // Первоначальная инициализация.Не хочу выносить в xml конфиг или файл - лень руками набивать
        int j = 3;
        for(int i=1;i<100;i++ ) {
            DriverPerson dr = new DriverPerson();
            dr.setId(UUID.randomUUID().toString());
            dr.setBirthday(new DateTime(getRandomInterval(1960, 1997), getRandomInterval(1, 12),getRandomInterval(2, 28), 0, 0));
            dr.setName(fname.get(getRandomInterval(0, 5)));
            dr.setLastName(lname.get(getRandomInterval(0, 5)));
            dr.setSecondName(sname.get(getRandomInterval(0, 5)));
            dr.setSex((i & 1) == 1);
            UUID_DRIVER_MAP.put(dr.getId(), dr);
            if(j-- >0){
                ONE_GRID_MAP.add(dr.getId());
            }
        }


    }
    /**
     * Получаю случайное целое на заданном интервале
     * @param min
     * @param max
     * @return
     */
    private static int getRandomInterval(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public DriverPersonRepositoryImpl(){

    }



    @Override
    public List<DriverPerson> getAll() {
          return new LinkedList<DriverPerson>(UUID_DRIVER_MAP.values() );
    }


    /**
     * Получить из таблицы условного документа всех внесенных
     * @return
     */
    @Override
    public Set<DriverPerson> getFromGridDrvAll(){
        Set<DriverPerson> result = new HashSet<>();
        for (String gridDrvId :ONE_GRID_MAP) {
            result.add(UUID_DRIVER_MAP.get(gridDrvId));
        }
        return result;
    };

    @Override
    public DriverPerson getByID(String id) {
        return UUID_DRIVER_MAP.get(id);
    }

    @Override
    public List<DriverPerson> getByPartFio(String partFio) {
        List<DriverPerson> result = new ArrayList<>();
        for(Map.Entry<String, DriverPerson> entryDP:UUID_DRIVER_MAP.entrySet() ){
            DriverPerson dp= entryDP.getValue();

            String  sourceSearch=new StringBuilder().append(dp.getName()).append(" ").append(dp.getLastName()).append(" ").append( dp.getSecondName()).toString();
            if(sourceSearch.contains(partFio)){
                result.add(dp);
            }
        }

        return result;
    }

    @Override
    public boolean delGridRecDriver(String id) {
        boolean res;
        try{
            if(ONE_GRID_MAP.contains(id)){
                ONE_GRID_MAP.remove(id);
                res=true;
            }else{
                res=false;
            }
        }catch (Exception e){
            res=false;
        }
        return res;
    }

    public String putPerson(DriverPerson person){
        String result="";
        try{
            UUID_DRIVER_MAP.put(person.getId(),person);
            ONE_GRID_MAP.add(person.getId());
            result=person.getId();
        }catch (Exception e){
            result="error";
        }
        return result;
    }
}
