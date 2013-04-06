// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package dk.clanie.bitcoin.client.response;

import dk.clanie.bitcoin.client.Transaction;
import dk.clanie.bitcoin.client.response.GetBlockTemplateResult;
import dk.clanie.bitcoin.client.response.JsonObject;
import java.util.Date;

privileged aspect GetBlockTemplateResult_Roo_JavaBean {
    
    public Integer GetBlockTemplateResult.getVersion() {
        return this.version;
    }
    
    public String GetBlockTemplateResult.getPreviousBlockHash() {
        return this.previousBlockHash;
    }
    
    public Transaction[] GetBlockTemplateResult.getTransactions() {
        return this.transactions;
    }
    
    public JsonObject GetBlockTemplateResult.getCoinBaseAux() {
        return this.coinBaseAux;
    }
    
    public Transaction GetBlockTemplateResult.getCoinBaseTransaction() {
        return this.coinBaseTransaction;
    }
    
    public Long GetBlockTemplateResult.getCoinBaseValue() {
        return this.coinBaseValue;
    }
    
    public String GetBlockTemplateResult.getTarget() {
        return this.target;
    }
    
    public Date GetBlockTemplateResult.getMinTime() {
        return this.minTime;
    }
    
    public String[] GetBlockTemplateResult.getMutable() {
        return this.mutable;
    }
    
    public String GetBlockTemplateResult.getNonceRange() {
        return this.nonceRange;
    }
    
    public Integer GetBlockTemplateResult.getSigOpLimit() {
        return this.sigOpLimit;
    }
    
    public Integer GetBlockTemplateResult.getSizeLimit() {
        return this.sizeLimit;
    }
    
    public Date GetBlockTemplateResult.getCurTime() {
        return this.curTime;
    }
    
    public String GetBlockTemplateResult.getBits() {
        return this.bits;
    }
    
    public Long GetBlockTemplateResult.getHeight() {
        return this.height;
    }
    
    public String GetBlockTemplateResult.getWorkId() {
        return this.workId;
    }
    
}