package edu.washington.pypark.lifecounter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

data class PlayerModel(var lifePoint: Int)

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = PlayerListAdapter(this)


    }

    private class PlayerListAdapter(context: Context): BaseAdapter() {
        private val playerScores = arrayListOf<PlayerModel>(PlayerModel(20), PlayerModel(20), PlayerModel(20), PlayerModel(20))

        private val mContext: Context

        init {
            this.mContext = context
        }
        // how many rows in my list
        override fun getCount(): Int {
            return playerScores.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        // rendering out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val main = layoutInflater.inflate(R.layout.activity_main, parent, false)
            val main2 = layoutInflater.inflate(R.layout.row_main, parent, false)

            val positionTextView = main2.findViewById<TextView>(R.id.playerNumber)
            var lifePointValue = main2.findViewById<TextView>(R.id.lifePointValue)
            var currentPosition = position + 1

            // buttons
            val minusButton = main2.findViewById<Button>(R.id.minus)
            val addButton = main2.findViewById<Button>(R.id.add)
            val minus5Button = main2.findViewById<Button>(R.id.minus5)
            val add5Button = main2.findViewById<Button>(R.id.add5)

            positionTextView.text = "$currentPosition"
            lifePointValue.text = playerScores.get(position).lifePoint.toString()

            minusButton.setOnClickListener {
                //do something
               playerScores.get(position).lifePoint = playerScores.get(position).lifePoint - 1
                lifePointValue.text = playerScores.get(position).lifePoint.toString()
                if (playerScores.get(position).lifePoint <= 0) {
                    val loser = main.findViewById<TextView>(R.id.lost)
                    loser.text = "Player $currentPosition LOSES!"
                    loser.visibility = TextView.VISIBLE
                }
                notifyDataSetChanged()
            }
            addButton.setOnClickListener {
                playerScores.get(position).lifePoint = playerScores.get(position).lifePoint + 1
                lifePointValue.text = playerScores.get(position).lifePoint.toString()
                notifyDataSetChanged()
            }

            minus5Button.setOnClickListener {
                playerScores.get(position).lifePoint = playerScores.get(position).lifePoint - 5
                lifePointValue.text = playerScores.get(position).lifePoint.toString()
                if (playerScores.get(position).lifePoint <= 0) {
                    val loser: TextView = main.findViewById(R.id.lost)
                    loser.text = "Player $currentPosition LOSES!"
                    loser.setVisibility(View.GONE);
                    Toast.makeText(mContext, loser.text, Toast.LENGTH_LONG).show()

                }
                notifyDataSetChanged()
            }
            add5Button.setOnClickListener {
                playerScores.get(position).lifePoint = playerScores.get(position).lifePoint + 5
                lifePointValue.text = playerScores.get(position).lifePoint.toString()
                notifyDataSetChanged()
            }

            return main2
        }

    }
}
