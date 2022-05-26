package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.GateEntityPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.rulecmd.missions.GateCMD;
import exerelin.campaign.ExerelinSetupData;

public class stg_ModPlugin extends BaseModPlugin {

    public void onNewGameAfterTimePass() {
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        if (haveNexerelin) {
            boolean activateThoseGates = ExerelinSetupData.getInstance().skipStory;
            if (activateThoseGates) {
                for (SectorEntityToken gate : Global.getSector().getCustomEntitiesWithTag(Tags.GATE)) {
                    String gateName = gate.getName();
                    if (!gateName.contains("Inactive") && !gate.getMemoryWithoutUpdate().getBoolean(GateEntityPlugin.GATE_SCANNED)) {
                        gate.getMemory().set(GateEntityPlugin.GATE_SCANNED, true);
                        GateCMD.notifyScanned(gate);
                    }
                }
            }
        }
    }
}