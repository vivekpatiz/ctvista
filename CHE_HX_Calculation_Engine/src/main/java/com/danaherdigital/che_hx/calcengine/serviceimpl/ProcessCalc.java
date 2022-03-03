package com.danaherdigital.che_hx.calcengine.serviceimpl;

import java.util.Map;
import java.util.concurrent.Callable;

import com.danaherdigital.che_hx.calcengine.service.ICalcService;

public class ProcessCalc implements Callable<Map<String,Object>>
{
   private Map<String, Object> input;
   private ICalcService calcService;
   public ProcessCalc(Map<String, Object> input,ICalcService calcService )
   {
       this.input=input;
       this.calcService=calcService;
   }

   public Map<String,Object> call() throws Exception
   {

    return   calcService.calcProcess(input);
    }


 }