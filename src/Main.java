import java.util.Random;

public class Main {
    public static int bossHealth = 700;

    public static int bossDamage = 50;

    public static String bossDefence;

    public static int[] heroesHealth = {   280,    270,    250,  300};

    public static int[] heroesDamage = {   10,    15,    20,    0};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};

    public static int roundNumber;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinish()){
            playRound();
        }
    }

    public static void playRound(){
        roundNumber ++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        printStatistics();
        medicSkill();
    }

    private static void medicSkill() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i].equals("Medic") ){
                continue;
            }else if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0 ){
                heroesHealth[i]=+250;
                System.out.println("Medic healed" + heroesAttackType[i]);
                break;
            }

        }
    }

    public static void chooseBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesHealth.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void heroesHits(){
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence ){
                    Random random = new Random();
                    int coefficient = random.nextInt(9)+ 2;//2,3,4,5,6,7,8,9,10
                    damage = coefficient * heroesDamage[i];
                    System.out.println("Critical damage: " + damage);
                }
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }

                }
            }
        }

    public static boolean isGameFinish(){
        if (bossHealth <= 0 ){
            System.out.println("Heroes won!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0){
            System.out.println("Boss won!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }

    public static void printStatistics(){
        /*String defence = "No defence";
        if (bossDefence != null ){
            defence = bossDefence;
        }*/

        System.out.println("ROUND " + roundNumber + " ---------- ");
        System.out.println("Boss health " + bossHealth + "  damage: " + bossDamage
                + " defence: " + ( bossDefence != null ? bossDefence :  "No defence" ));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + "health " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }

    }
}