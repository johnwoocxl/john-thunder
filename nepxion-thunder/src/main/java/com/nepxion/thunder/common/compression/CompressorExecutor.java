package com.nepxion.thunder.common.compression;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.facebook.util.compress.QuickLz;
import com.nepxion.thunder.common.entity.CompressorType;

public class CompressorExecutor {
    public static byte[] compress(byte[] bytes) {
        return compress(bytes, CompressorFactory.getCompressorType());
    }

    public static byte[] decompress(byte[] bytes) {
        return decompress(bytes, CompressorFactory.getCompressorType());
    }

    public static byte[] compress(byte[] bytes, CompressorType type) {
        if (type == CompressorType.QUICK_LZ_COMPRESSOR) {
            return QuickLz.compress(bytes);
        } else {
            throw new CompressorException("Invalid compressor type : " + type);
        }
    }

    public static byte[] decompress(byte[] bytes, CompressorType type) {
        if (type == CompressorType.QUICK_LZ_COMPRESSOR) {
            return QuickLz.decompress(bytes);
        } else {
            throw new CompressorException("Invalid compressor type : " + type);
        }
    }
}