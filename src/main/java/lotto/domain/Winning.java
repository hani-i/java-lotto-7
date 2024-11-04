package lotto.domain;

import lotto.utils.Parsing;

import java.util.ArrayList;
import java.util.List;

public enum Winning {
    FIRST(6,"2,000,000,000", 0),
    SECOND(5,"30,000,000", 0),
    THIRD(5,"1,500,000", 0),
    FOURTH(4,"50,000",0),
    FIFTH(3,"5,000",0);

    private int numberOfMatches;
    private String prizeMoney;
    private int numberOfLottos;

    Winning(int numberOfMatches, String prizeMoney, int numberOfLottos){
        this.numberOfMatches = numberOfMatches;
        this.prizeMoney = prizeMoney;
        this.numberOfLottos = numberOfLottos;
    }

    public List<Winning> findWinningDetail(List<Lotto> lottos, String winningNumberInput, String bonusNumberInput) {
        Parsing parsing = new Parsing();
        int getvalidNumber = 0;
        int getvalidBonus = 0;
        List<Integer> getWinningNumber = parsing.stringToIntegerArray(winningNumberInput);
        for(Lotto lotto : lottos){
            getvalidNumber = compareLottoAndWinning(lotto.getLotto(),getWinningNumber);
            getvalidBonus = compareLottoAndBonus(lotto.getLotto(),parsing.stringToInteger(bonusNumberInput));
            if (3<= getvalidNumber){
                increaseNumberOfLottos(getvalidNumber,getvalidBonus);
            }
        }

        List<Winning> winningResults = new ArrayList<>();
        for (Winning winning : Winning.values()) {
            winningResults.add(winning); // 상수 그대로 추가
        }
        return winningResults;
    }

    public int compareLottoAndWinning(List<Integer> lotto, List<Integer> getWinningNumber){
        int matchingNumber = 0;
        for(int i=0; i<6; i++){
            boolean hasWinningNumber = lotto.contains(getWinningNumber.get(i));
            if(hasWinningNumber){
                matchingNumber += 1;
            }
        }
        return matchingNumber;
    }

    public int compareLottoAndBonus(List<Integer> lotto, int bonusNumberInput){
        int matchingNumber = 0;
        for(int i=0; i<6; i++){
            boolean hasWinningNumber = lotto.contains(bonusNumberInput);
            if(hasWinningNumber){
                matchingNumber += 1;
            }
        }
        return matchingNumber;
    }

    public void increaseNumberOfLottos(int getvalidNumber, int getvalidBonus){
        for (Winning winning : Winning.values()) {
            if (winning.numberOfMatches == 5 && getvalidNumber == 5 && getvalidBonus==1) {
                winning.numberOfLottos += 1;
                break;
            }

            if (winning.numberOfMatches == getvalidNumber) {
                winning.numberOfLottos += 1;
                break;
            }
        }

    }

}