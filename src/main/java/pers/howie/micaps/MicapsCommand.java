package pers.howie.micaps;

import org.springframework.stereotype.Component;
import pers.howie.micaps.entity.Micaps4;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import ucar.ma2.Array;
import ucar.ma2.DataType;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFileWriter;
import ucar.nc2.Variable;

import java.io.File;
import java.util.concurrent.Callable;

@Component
@Command(name = "micaps",
        helpCommand = true
)
public class MicapsCommand implements Callable<Integer> {

    @Option(
            names = "-i",
            required = true,
            description = "输入文件"
    )
    private File file;

    public Integer call() throws Exception {
        if (!file.exists()) throw new IllegalArgumentException("文件不存在");

        Micaps4 m4 = new Micaps4Reader(file).read();
        float[] lats = m4.getLats();
        float[] lons = m4.getLons();


        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, "output.nc");

        writer.addDimension(null, "lat", lats.length);
        writer.addDimension(null, "lon", lons.length);

        Variable latVar = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable lonVar = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable dataVar = writer.addVariable(null, "data", DataType.FLOAT, "lat lon");

        latVar.addAttribute(new Attribute("units", "degrees_north"));
        lonVar.addAttribute(new Attribute("units", "degrees_east"));

        writer.create();
        writer.write(latVar, Array.factory(lats));
        writer.write(lonVar, Array.factory(lons));
        writer.write(dataVar, Array.factory(m4.getData()));

        writer.close();
        return 0;
    }
}