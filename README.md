# MojangAPI

###### MojangAPI para JAVA

## Gradle 
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies { 
    compile group: 'com.github.NiotGGProjects', name: 'MojangAPI', version: 'master-SNAPSHOT'
}
```

## Maven
```apache
<project>
    <repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
    </repositories>
	
    <dependencies>
	<dependency>
    	    <groupId>com.github.NiotGGProjects</groupId>
    	    <artifactId>MojangAPI</artifactId>
    	    <version>master-SNAPSHOT</version>
    	</dependency>
    </dependencies>
</project>
```

## Download JAR
[MojangApi.jar](https://github.com/NiotGGProjects/MojangAPI/releases/download/1.0/MojangApi.jar)
[MojangApi-WhithDependencies.jar](https://github.com/NiotGGProjects/MojangAPI/releases/download/1%2C0/MojangApi-WhithDependencies.jar)

## Exemplo de uso da MojangAPI

```java
 public static void main(String[] args) {
        Mojang mojang = new Mojang();
        if (!mojang.isOnline()) {
            System.out.println("Os servidores da API da Mojang estão offline");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        System.out.print("Username: ");
        String username = input.next();
        String uuid = mojang.getUUIDByNickName(username);
        if (uuid == null) {
            System.out.println("Esse username não existe no banco de dados da mojang!");
            return;
        }
        HistoryName history = mojang.getHistoryNameByUUID(uuid);
        if (history == null) {
            System.out.println("Esse UUID não existe no banco de dados da mojang!");
            return;
        }

        System.out.println("---------------------------");
        Profile p = mojang.getProfileByUUID(uuid);
        System.out.println("Nick atual: " + p.getName());
        if (p.isLegacy()) {
            System.out.println("Estado: Unmigrated");
        } else if (p.isDemo()) {
            System.out.println("Estado: Demo");
        } else {
            System.out.println("Estado: Premium");
        }
        System.out.println();
        if (p.getSkin_url() == null) {
            System.out.println("Skin url: Skin Padrão");
        } else {
            System.out.println("Skin url: " + p.getSkin_url());
        }
        if (p.getCape_url() == null) {
            System.out.println("Cape url: Sem Capa");
        } else {
            System.out.println("Cape url: " + p.getCape_url());
        }
        System.out.println();
        System.out.println("Historico de NickNames: ");
        for (int i = history.getNickNames().size(); i > 0; i--) {
            Long miriliss = history.getChangedToAt(i - 1);
            if (miriliss == 0) {
                System.out.println("Username: " + history.getName(i - 1));

            } else {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                System.out.println("Username: " + history.getName(i - 1) + " * " + dateFormat.format(new Date(miriliss)));
            }
        }

        System.out.println("---------------------------");
    }
```

***Uma simples API eu tinha feito para uso próprio mas resolvi disponibilizar, irei atualiza-la e adicionar recursos ao tempo, se tiverem alguma dúvida ou para uma sugestão a essa API me chame no discord NiotGG#7267***
