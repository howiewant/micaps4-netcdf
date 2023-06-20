# micaps4-netcdf
micaps 4 类数据转netcdf

使用spring native + graalvm 打包成可执行文件
```shell
./mvnw native:compile -Pnative  
```
通过 -i 参数指定micaps4类文件，输出会在当前目录下生成一个output.nc文件
```shell
target/micaps -i /path/to/micaps
```
