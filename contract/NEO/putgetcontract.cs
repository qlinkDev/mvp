using Neo.SmartContract.Framework.Services.Neo;
using System;
using System.Collections;

namespace Neo.SmartContract
{
    public class Wifi_Contract : Framework.SmartContract
    {

        public static Object Main(string operation, params object[] args)
        {

            if (operation == "put")
            {
                return put((string)args[0], (string)args[1]);
            }

            if (operation == "get")
            {
                return get((string)args[0]);
            }

            return false;
        }
        // 存储
        public static string[] put(string key, string value)
        {

            string[] result = new string[2];
            byte[] byteArray = Storage.Get(Storage.CurrentContext, key);
            if (byteArray.Length > 0)
            {
                result[0] = "0";
                result[1] = "exists";
                return result;
            }
            Storage.Put(Storage.CurrentContext, key, value);

            result[0] = "1";
            result[1] = "success";
            return result;
        }
        // 获取
        public static byte[] get(string key)
        {
            return Storage.Get(Storage.CurrentContext, key);
        }
        
        public static bool set(string key)
        {
            return false;
        }

    }
}