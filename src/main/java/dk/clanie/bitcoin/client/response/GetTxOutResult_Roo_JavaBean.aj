// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package dk.clanie.bitcoin.client.response;

import dk.clanie.bitcoin.ScriptPubKey;
import dk.clanie.bitcoin.client.response.GetTxOutResult;
import java.math.BigDecimal;

privileged aspect GetTxOutResult_Roo_JavaBean {
    
    public String GetTxOutResult.getBestBlock() {
        return this.bestBlock;
    }
    
    public Integer GetTxOutResult.getConfirmations() {
        return this.confirmations;
    }
    
    public BigDecimal GetTxOutResult.getValue() {
        return this.value;
    }
    
    public ScriptPubKey GetTxOutResult.getScriptPubKey() {
        return this.scriptPubKey;
    }
    
    public Integer GetTxOutResult.getVersion() {
        return this.version;
    }
    
    public Boolean GetTxOutResult.getCoinBase() {
        return this.coinBase;
    }
    
}