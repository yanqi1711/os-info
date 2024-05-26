package org.example;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.util.List;

public class Main {

    private static final SystemInfo si = new SystemInfo();

    public static void main(String[] args) {

        HardwareAbstractionLayer hardware = si.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
        networkIFs.stream()
                .map(NetworkIF::getName)
                .map(name -> name.startsWith("en") || name.startsWith("eth") ? name : null)
                .forEach(name -> {if(name != null) System.out.println(name);});
        System.out.println(hardware.getComputerSystem().getModel());
        System.out.println(hardware.getMemory().getTotal() / 1024.0 / 1024 / 1024);
        System.out.println(processor.getSystemCpuLoadTicks()[0]);
    }
}