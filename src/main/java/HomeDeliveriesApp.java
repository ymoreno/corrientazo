import com.corrientazoadomicilio.service.DeliveryReportWriterService;
import com.corrientazoadomicilio.service.DroneMovementService;
import com.corrientazoadomicilio.service.FileReaderService;
import com.corrientazoadomicilio.service.impl.DeliveryReportWriterServiceImpl;
import com.corrientazoadomicilio.service.impl.DroneMovementServiceImpl;
import com.corrientazoadomicilio.service.impl.FileReaderServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HomeDeliveriesApp {

    private static final Logger logger = Logger.getLogger(HomeDeliveriesApp.class);

    public static void main (String... args) {
        BasicConfigurator.configure();
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        DroneMovementService droneMovementService = new DroneMovementServiceImpl();
        DeliveryReportWriterService deliveryReportWriterService = new DeliveryReportWriterServiceImpl();
        try {
            List<List<String>> positions = fileReaderService.getDroneMovements().stream()
                    .map(droneMovementService::moveDrone)
                    .collect(Collectors.toList());
            AtomicInteger counter = new AtomicInteger(1);
            positions.forEach(positionsList-> {
                try {
                    deliveryReportWriterService.writeReport(positionsList,counter.getAndIncrement());
                } catch (IOException e) {
                    logger.error("Ha ocurrido un error al intentar escribir el reporte de entrega: ", e);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            logger.error("Ha ocurrido un error al intentar leer los archivos de entrada: ", e);
            e.printStackTrace();
        }
    }

}
